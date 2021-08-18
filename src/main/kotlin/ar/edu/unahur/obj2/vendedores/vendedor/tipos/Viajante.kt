package ar.edu.unahur.obj2.vendedores.vendedor.tipos

import ar.edu.unahur.obj2.vendedores.Ciudad
import ar.edu.unahur.obj2.vendedores.Provincia
import ar.edu.unahur.obj2.vendedores.vendedor.Vendedor

class Viajante(val provinciasHabilitadas: List<Provincia>) : Vendedor() {
    override fun puedeTrabajarEn(ciudad: Ciudad): Boolean {
        return provinciasHabilitadas.contains(ciudad.provincia)
    }

    override fun esInfluyente(): Boolean {
        val poblacionDeCiudadesHabilitadas = provinciasHabilitadas.sumBy { provincia -> provincia.poblacion }
        return poblacionDeCiudadesHabilitadas >= 10000000
    }
}