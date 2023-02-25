package com.como.hogar.bussines.services.clientes;

import com.como.hogar.bussines.services.referencias.ReferenceReaderService;
import com.como.hogar.common.enumeration.EstadoEnum;
import com.como.hogar.common.exceptions.ClienteException;
import com.como.hogar.common.mappers.ClienteMapper;
import com.como.hogar.common.util.ClientValidationUtil;
import com.como.hogar.common.util.MessageFormatUtil;
import com.como.hogar.data.entities.cliente.Cliente;
import com.como.hogar.data.repositories.ClienteRepository;
import com.como.hogar.web.dtos.ClienteTo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.locks.StampedLock;

@Service
@Transactional(readOnly = true)
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final MessageFormatUtil messageFormatUtil;
    private final ReferenceReaderService referenceReaderService;
    private StampedLock lock;

    public ClienteService(ClienteRepository clienteRepository, MessageFormatUtil messageFormatUtil,
                          ReferenceReaderService referenceReaderService) {
        this.clienteRepository = clienteRepository;
        this.messageFormatUtil = messageFormatUtil;
        this.referenceReaderService = referenceReaderService;
        lock = new StampedLock();
    }

    @Cacheable("CLIENTES")
    public Page<ClienteTo> getAll(int page, int size) {
        return clienteRepository.findAllByEstado(PageRequest.of(page, size),
                EstadoEnum.ACTIVO.getEstado()).map(cliente -> ClienteMapper.toTo(cliente));
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "CLIENTES", allEntries = true),
            @CacheEvict(value = "CLIENTE_BY_ID", allEntries = true),
            @CacheEvict(value = "CLIENTE_BY_NAME", allEntries = true)})
    public ClienteTo crearCliente(ClienteTo clienteTo) {
        ClientValidationUtil.checkValidClienteTo(clienteTo);
        Optional<Cliente> optionalCliente = clienteRepository.findByNombreOrEmail(clienteTo.getNombre(),
                clienteTo.getEmail());

        if (optionalCliente.isPresent()) {
            throw new ClienteException(messageFormatUtil.getMessage("cliente.busca.nombre.o.email.ya.existe",
                    clienteTo.getNombre(), clienteTo.getEmail()));
        }

        referenceReaderService.seleccionarBeneficio(clienteTo);
        Cliente cliente = clienteRepository.save(ClienteMapper.toEntity(clienteTo));

        return ClienteMapper.toTo(cliente);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "CLIENTES", allEntries = true),
            @CacheEvict(value = "CLIENTE_BY_ID", allEntries = true),
            @CacheEvict(value = "CLIENTE_BY_NAME", allEntries = true)})
    public void actualizarCliente(ClienteTo clienteTo, Long id) {
        ClientValidationUtil.checkValidClienteTo(clienteTo);

        long stamp = lock.writeLock();
        try {
            Cliente cliente = getClientById(id);
            cliente.setNombre(clienteTo.getNombre());
            cliente.setEmail(clienteTo.getEmail());
            cliente.setTelefono(clienteTo.getTelefono());
            clienteRepository.save(cliente);
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "CLIENTES", allEntries = true),
            @CacheEvict(value = "CLIENTE_BY_ID", allEntries = true),
            @CacheEvict(value = "CLIENTE_BY_NAME", allEntries = true)})
    public void eliminarCliente(Long id) {
        long stamp = lock.writeLock();

        try {
            Cliente cliente = getClientById(id);
            cliente.setEstado(EstadoEnum.INACTIVO.getEstado());
            clienteRepository.save(cliente);
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    @Cacheable(value = "CLIENTE_BY_ID")
    public Cliente getClientById(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new ClienteException(messageFormatUtil.getMessage("{cliente" +
                ".busca.id.no.existe}", id)));
    }

    @Cacheable(value = "CLIENTE_BY_NAME")
    public Cliente getClienteByName(String nombre) {
        return clienteRepository.findByNombre(nombre).orElseThrow(() -> new ClienteException(messageFormatUtil.getMessage("{cliente" +
                ".busca.nombre.no.existe}", nombre)));
    }
}
