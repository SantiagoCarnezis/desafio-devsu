package com.scarnezis.desafio_devsu_microservicio_movimientos.service;

import com.scarnezis.desafio_devsu_microservicio_movimientos.dto.cuenta.CrearCuenta;
import com.scarnezis.desafio_devsu_microservicio_movimientos.entity.Cuenta;
import com.scarnezis.desafio_devsu_microservicio_movimientos.entity.EstadoCuenta;
import com.scarnezis.desafio_devsu_microservicio_movimientos.excepcion.CuentaNoEncontradaException;
import com.scarnezis.desafio_devsu_microservicio_movimientos.repository.CuentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class CuentaService {

    private final CuentaRepository cuentaRepository;
    private final String cierreCuentaTopic = "cierre-cuenta-topic";

    public List<Cuenta> getAllCuentas(String clienteId) {

        return cuentaRepository.findAllByDuenioId(clienteId);
    }

    public Cuenta getCuentaByNumero(String numeroCuenta) {

        return cuentaRepository.findById(numeroCuenta).orElseThrow(() -> new CuentaNoEncontradaException(numeroCuenta));
    }

    public Cuenta createCuenta(CrearCuenta cuentaDto) {

        Cuenta cuenta = new Cuenta();
        cuenta.setDuenioId(cuentaDto.getDuenioId());
        cuenta.setSaldo(1000.0);
        cuenta.setTipo(cuentaDto.getTipo());
        cuenta.setEstado(EstadoCuenta.ABIERTA);

        return cuentaRepository.save(cuenta);
    }

    public void deleteCuenta(String numeroCuenta) {

        cuentaRepository.deleteById(numeroCuenta);
    }

    @KafkaListener(topics = cierreCuentaTopic)
    public void cerrarCuenta(String numeroCuenta) {

        Cuenta cuenta = cuentaRepository.findById(numeroCuenta).orElseThrow(() -> new CuentaNoEncontradaException(numeroCuenta));
        cuenta.setEstado(EstadoCuenta.CERRADA);
        cuentaRepository.save(cuenta);
    }
}

