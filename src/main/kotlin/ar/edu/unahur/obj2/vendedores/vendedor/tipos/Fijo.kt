package ar.edu.unahur.obj2.vendedores.vendedor.tipos

import ar.edu.unahur.obj2.vendedores.Ciudad
import ar.edu.unahur.obj2.vendedores.vendedor.Vendedor

class Fijo (val ciudadOrigen: Ciudad) : Vendedor() {
    override fun puedeTrabajarEn(ciudad: Ciudad): Boolean {
        return ciudad == ciudadOrigen
    }

    override fun esInfluyente(): Boolean {
        return false
    }
}