package ar.edu.unahur.obj2.vendedores

import ar.edu.unahur.obj2.vendedores.vendedor.tipos.ComercioCorresponsal
import ar.edu.unahur.obj2.vendedores.vendedor.tipos.Fijo
import ar.edu.unahur.obj2.vendedores.vendedor.tipos.Viajante
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue

class VendedorTest : DescribeSpec({
  val misiones = Provincia(1300000)
  val buenosAires = Provincia(16660000)
  val cordoba = Provincia(2000000)
  val entreRios = Provincia(400000)
  val chubut = Provincia(3000000)
  val mendoza = Provincia(2000000)
  val sanIgnacio = Ciudad(misiones)
  val obera = Ciudad(misiones)
  val villaBosch = Ciudad(buenosAires)
  val villaDolores = Ciudad(cordoba)
  val parana = Ciudad(entreRios)
  val concepcionDelUruguay = Ciudad(entreRios)
  val gualeguay = Ciudad(entreRios)
  val concordia = Ciudad(entreRios)
  val rawson = Ciudad(chubut)
  val sanMartin = Ciudad(mendoza)

  describe("Fijo") {
    val fijo = Fijo(obera)
    describe("puedeTrabajarEn") {
      it("Su ciudad de origen") {
        fijo.puedeTrabajarEn(obera).shouldBeTrue()
      }
      it("Otra ciudad") {
        fijo.puedeTrabajarEn(sanIgnacio).shouldBeFalse()
      }
    }
    describe("esInfluyente") {
      it("Un vendedor fijo nunca es influyente") {
        fijo.esInfluyente().shouldBeFalse()
      }
    }
  }

  describe("Viajante") {
    val viajante = Viajante(listOf(misiones, buenosAires))
    describe("puedeTrabajarEn") {
      it("Una ciudad que pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(sanIgnacio).shouldBeTrue()
      }
      it("Una ciudad que no pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(villaDolores).shouldBeFalse()
      }
    }
    describe("esInfluyente") {
      it("Las provincias habilitadas suman mas de 10 millones") {
        viajante.esInfluyente().shouldBeTrue()
      }
      it("Las provincias habilitadas no suman mas de 10 millones") {
        val viajanteProvinciano = Viajante(listOf(misiones))
        viajanteProvinciano.esInfluyente().shouldBeFalse()
      }
    }
  }

  describe("ComercioDeCorresponsal") {
    describe("puedeTrabajarEn") {
      val comercio = ComercioCorresponsal(listOf(sanIgnacio, villaBosch))
      it("Una ciudad que pertenece a una ciudad habilitada"){
        comercio.puedeTrabajarEn(sanIgnacio).shouldBeTrue()
      }
      it("Una ciudad que no pertenece a una ciudad habilitada"){
        comercio.puedeTrabajarEn(rawson).shouldBeFalse()
      }
    }
    describe("esInfluyente") {
      describe("Cantidad de ciudades") {
        it("Cantidad de ciudades mayor o igual a 5") {
          val comercio = ComercioCorresponsal(listOf(parana, concepcionDelUruguay, gualeguay, concordia, sanMartin))
          comercio.esInfluyente().shouldBeTrue()
        }
        it("Cantidad de ciudades menor a 5") {
          val comercio = ComercioCorresponsal(listOf(parana, concepcionDelUruguay, gualeguay))
          comercio.esInfluyente().shouldBeFalse()
        }
      }
      describe("Cantidad de provincias") {
        it("Cantidad de provincias mayor o igual a 3") {
          val comercio = ComercioCorresponsal(listOf(parana, sanMartin, rawson))
          comercio.esInfluyente().shouldBeTrue()
        }
        it("Cantidad de provincias menor a 3") {
          val comercio = ComercioCorresponsal(listOf(parana, concepcionDelUruguay, gualeguay, concordia))
          comercio.esInfluyente().shouldBeFalse()
        }
      }
    }
  }
})
