package ar.edu.unahur.obj2.vendedores.vendedor

import ar.edu.unahur.obj2.vendedores.Ciudad

abstract class Vendedor {

  val certificaciones = mutableListOf<Certificacion>()

  abstract fun puedeTrabajarEn(ciudad: Ciudad): Boolean

  fun esVersatil() =
    certificaciones.size >= 3
      && this.certificacionesDeProducto() >= 1
      && this.otrasCertificaciones() >= 1

  fun agregarCertificacion(certificacion: Certificacion) {
    certificaciones.add(certificacion)
  }

  fun esFirme() = this.puntajeCertificaciones() >= 30

  fun certificacionesDeProducto() = certificaciones.count { it.esDeProducto }

  fun otrasCertificaciones() = certificaciones.count { !it.esDeProducto }

  fun puntajeCertificaciones() = certificaciones.sumBy { c -> c.puntaje }

  abstract fun esInfluyente() : Boolean
}
