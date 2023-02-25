package com.como.hogar.referencias;

import com.como.hogar.bussines.services.clientes.ClienteService;
import com.como.hogar.bussines.services.referencias.ReferenceReaderService;
import com.como.hogar.common.enumeration.GrupoBeneficioEnum;
import com.como.hogar.common.util.MessageFormatUtil;
import com.como.hogar.data.entities.cliente.Cliente;
import com.como.hogar.referencias.base.BaseTest;
import com.como.hogar.web.dtos.ClienteTo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ClienteServiceTest extends BaseTest {

    @Autowired
    private ReferenceReaderService referenceReaderService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private MessageFormatUtil messageFormatUtil;


    @Test
    public void testCrearCliente() {
        ClienteTo clienteTo = new ClienteTo();
        clienteTo.setNombre("John Doe");
        clienteTo.setEmail("johndoe@example.com");
        clienteTo.setTelefono("1234567890");
        clienteTo.setGrupoBeneficio(GrupoBeneficioEnum.TH);
        clienteTo.setBeneficio("Descuento1");

        ClienteTo createdCliente = clienteService.crearCliente(clienteTo);

        Assert.assertNotNull(createdCliente.getId());
        Assert.assertEquals(clienteTo.getNombre(), createdCliente.getNombre());
        Assert.assertEquals(clienteTo.getEmail(), createdCliente.getEmail());
        Assert.assertEquals(clienteTo.getTelefono(), createdCliente.getTelefono());
        Assert.assertEquals(clienteTo.getGrupoBeneficio(), createdCliente.getGrupoBeneficio());
        Assert.assertEquals(Arrays.asList(clienteTo.getBeneficio()).toString(), createdCliente.getBeneficio());

        Cliente cliente = clienteService.getClientById(createdCliente.getId());
        Assert.assertNotNull(cliente);
        Assert.assertEquals("John Doe", cliente.getNombre());
    }

    @Test
    public void testGetAll() {
        ClienteTo clienteTo = new ClienteTo();
        clienteTo.setNombre("Cliente Actualizado");
        clienteTo.setEmail("cliente1@test.com");
        clienteTo.setTelefono("1234567890");
        clienteTo.setGrupoBeneficio(GrupoBeneficioEnum.TH);
        clienteTo.setBeneficio("Descuento2");
        ClienteTo createdCliente = clienteService.crearCliente(clienteTo);

        Page<ClienteTo> clientes = clienteService.getAll(0, 10);
        Assert.assertEquals(1, clientes.getContent().size());
    }

    @Test
    public void deberiaRetornarCliente() {
        ClienteTo clienteTo = new ClienteTo();
        clienteTo.setNombre("Cliente 4");
        clienteTo.setEmail("cliente4@test.com");
        clienteTo.setTelefono("1234567890");
        clienteTo.setGrupoBeneficio(GrupoBeneficioEnum.TH);
        clienteTo.setBeneficio("Descuento4");
        ClienteTo createdCliente = clienteService.crearCliente(clienteTo);
        Long idCliente = createdCliente.getId();

        Cliente clienteObtenido = clienteService.getClientById(idCliente);

        Assert.assertEquals(clienteTo.getNombre(), clienteObtenido.getNombre());
    }
}
