package ar.edu.unahur.obj2.vendedores.vendedor.tipos

import ar.edu.unahur.obj2.vendedores.Ciudad
import ar.edu.unahur.obj2.vendedores.Provincia
import ar.edu.unahur.obj2.vendedores.vendedor.Vendedor

class ComercioCorresponsal (val ciudades: List<Ciudad>) : Vendedor() {
    override fun puedeTrabajarEn(ciudad: Ciudad): Boolean {
        return ciudades.contains(ciudad)
    }

    private fun cantidadDeProvincias() : Int {
        val provincias = mutableListOf<Provincia>()
        for (ciudad in ciudades) {
            provincias.add(ciudad.provincia)
        }
        return provincias.toSet().size
    }

    override fun esInfluyente(): Boolean {
        return ciudades.size >= 5 || cantidadDeProvincias() >= 3
    }
}