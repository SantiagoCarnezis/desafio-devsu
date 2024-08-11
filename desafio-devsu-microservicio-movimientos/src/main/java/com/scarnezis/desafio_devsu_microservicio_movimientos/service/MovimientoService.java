package com.scarnezis.desafio_devsu_microservicio_movimientos.service;

import com.scarnezis.desafio_devsu_microservicio_movimientos.dto.Mensajes;
import com.scarnezis.desafio_devsu_microservicio_movimientos.dto.movimiento.CrearMovimientoDto;
import com.scarnezis.desafio_devsu_microservicio_movimientos.entity.Cuenta;
import com.scarnezis.desafio_devsu_microservicio_movimientos.entity.EstadoCuenta;
import com.scarnezis.desafio_devsu_microservicio_movimientos.entity.Movimiento;
import com.scarnezis.desafio_devsu_microservicio_movimientos.excepcion.CuentaCerradaException;
import com.scarnezis.desafio_devsu_microservicio_movimientos.excepcion.CuentaNoEncontradaException;
import com.scarnezis.desafio_devsu_microservicio_movimientos.excepcion.MovimientoNoEncontradoException;
import com.scarnezis.desafio_devsu_microservicio_movimientos.repository.CuentaRepository;
import com.scarnezis.desafio_devsu_microservicio_movimientos.repository.MovimientoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    public List<Movimiento> getAllMovimientos(String numeroCuenta) {

        return movimientoRepository.findAllByCuentaNumeroCuenta(numeroCuenta);
    }

    public Movimiento getMovimientoById(String id) {
        return movimientoRepository.findById(id).orElseThrow(() -> new MovimientoNoEncontradoException(id));
    }

    @Transactional
    public Movimiento realizarPago(CrearMovimientoDto movimientoDto) {

        Cuenta cuentaDeudor = cuentaRepository.findById(movimientoDto.getNumeroCuentaDeudor())
                .orElseThrow(() -> new CuentaNoEncontradaException(movimientoDto.getNumeroCuentaDeudor()));

        if(cuentaDeudor.getEstado().equals(EstadoCuenta.CERRADA))
            throw new CuentaCerradaException(Mensajes.CUENTA_CERRADA_REALIZAR_PAGO, movimientoDto.getNumeroCuentaDeudor());

        cuentaDeudor.restarSaldo(movimientoDto.getValor());

        Cuenta cuentaAcreedor = cuentaRepository.findById(movimientoDto.getNumeroCuentaAcreedor())
                .orElseThrow(() -> new CuentaNoEncontradaException(movimientoDto.getNumeroCuentaAcreedor()));

        if(cuentaAcreedor.getEstado().equals(EstadoCuenta.CERRADA))
            throw new CuentaCerradaException(Mensajes.CUENTA_CERRADA_RECIBIR_PAGO, movimientoDto.getNumeroCuentaDeudor());

        cuentaAcreedor.sumarSaldo(movimientoDto.getValor());

        Movimiento movimientoDeudor = new Movimiento();
        movimientoDeudor.setClienteId(cuentaAcreedor.getDuenioId());
        movimientoDeudor.setFecha(LocalDateTime.now());
        movimientoDeudor.setTipo(cuentaAcreedor.getTipo());
        movimientoDeudor.setValor(-movimientoDto.getValor());
        movimientoDeudor.setSaldo(cuentaDeudor.getSaldo());
        movimientoDeudor.setCuenta(cuentaDeudor);

        Movimiento movimientoAcreedor = new Movimiento();
        movimientoAcreedor.setClienteId(cuentaDeudor.getDuenioId());
        movimientoAcreedor.setFecha(LocalDateTime.now());
        movimientoAcreedor.setTipo(cuentaDeudor.getTipo());
        movimientoAcreedor.setValor(movimientoDto.getValor());
        movimientoAcreedor.setSaldo(cuentaAcreedor.getSaldo());
        movimientoAcreedor.setCuenta(cuentaAcreedor);

        movimientoRepository.save(movimientoDeudor);
        movimientoRepository.save(movimientoAcreedor);

        return movimientoDeudor;
    }
}
