package ar.edu.unahur.obj2.vendedores

import ar.edu.unahur.obj2.vendedores.vendedor.Vendedor
import ar.edu.unahur.obj2.vendedores.vendedor.tipos.Fijo

class CentroDeDistribucion (val ciudad : Ciudad, val vendedores: MutableList<Vendedor>) {

    fun agregarVendedor(vendedor : Vendedor) : Boolean {
        if (vendedores.contains(vendedor)) {
            error("El vendedor ya se encuentra registrado")
        } else {
            return vendedores.add(vendedor)
        }
    }

    fun vendedorEstrella() : Vendedor {
        var vendedorConMasCertificaciones = vendedores.first()
        for ( vendedor in vendedores ) {
            if (vendedor.certificaciones.size > vendedorConMasCertificaciones.certificaciones.size) {
                vendedorConMasCertificaciones = vendedor
            }
        }
        return vendedorConMasCertificaciones
    }

    fun sePuedeCubrir ( ciudad: Ciudad) : Boolean {
        for ( vendedor in vendedores ) {
            if ( vendedor.puedeTrabajarEn(ciudad) ) {
                return true;
            }
        }
        return false;
    }

    fun vendedoresGenericos () : MutableList<Vendedor> {
        val vendedoresGenericos = mutableListOf<Vendedor>()
        for (vendedor in vendedores) {
            if (vendedor.certificaciones.any { it.esDeProducto }) {
                vendedoresGenericos.add(vendedor)
            }
        }
        return vendedoresGenericos
    }

    fun esRobusto () : Boolean {
        val vendedoresFirmes = mutableListOf<Vendedor>()
        for ( vendedor in vendedores ) {
            if (vendedor.esFirme()) {
                vendedoresFirmes.add(vendedor)
            }
        }
        return vendedoresFirmes.size >= 3
    }
}