package ar.edu.unahur.obj2.vendedores

import ar.edu.unahur.obj2.vendedores.vendedor.Certificacion
import ar.edu.unahur.obj2.vendedores.vendedor.tipos.ComercioCorresponsal
import ar.edu.unahur.obj2.vendedores.vendedor.tipos.Fijo
import ar.edu.unahur.obj2.vendedores.vendedor.tipos.Viajante
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class CentroDeDistribucionTest : DescribeSpec ({
    val buenosAires = Provincia(16660000)
    val entreRios = Provincia(400000)
    val villaBosch = Ciudad(buenosAires)
    val villaBallester = Ciudad(buenosAires)
    val concepcionDelUruguay = Ciudad(entreRios)
    val gualeguay = Ciudad(entreRios)

    describe("agregarVendedor") {
        val fijo = Fijo(villaBosch)
        val viajante = Viajante(listOf(buenosAires, entreRios))
        val centroDeDistribucion = CentroDeDistribucion(villaBosch, mutableListOf(fijo))
        it("Se agrega un vendedor no registrado") {
            centroDeDistribucion.agregarVendedor(viajante).shouldBeTrue()
        }
        it("Se agrega un vendedor registrado") {
            shouldThrowAny {
                centroDeDistribucion.agregarVendedor(fijo)
            }
        }
    }

    describe("vendedorEstrella") {
        val fijo = Fijo(villaBosch)
        val viajante = Viajante(listOf(buenosAires, entreRios))
        var comercioCorresponsal = ComercioCorresponsal(mutableListOf(villaBosch, villaBallester,gualeguay, concepcionDelUruguay))
        it("El vendedor estrella es el fijo") {
            fijo.agregarCertificacion(Certificacion(false,8))
            fijo.agregarCertificacion(Certificacion(true,9))
            fijo.agregarCertificacion(Certificacion(false,5))
            viajante.agregarCertificacion(Certificacion(true,5))
            viajante.agregarCertificacion(Certificacion(false,7))
            comercioCorresponsal.agregarCertificacion(Certificacion(true,5))
            val centroDeDistribucion = CentroDeDistribucion(villaBosch, mutableListOf(fijo, viajante, comercioCorresponsal))
            fijo.shouldBe(centroDeDistribucion.vendedorEstrella())
        }
        it("El vendedor estrella es el viajante") {
            fijo.agregarCertificacion(Certificacion(false,8))
            viajante.agregarCertificacion(Certificacion(true,5))
            viajante.agregarCertificacion(Certificacion(false,7))
            comercioCorresponsal.agregarCertificacion(Certificacion(true,5))
            val centroDeDistribucion = CentroDeDistribucion(villaBosch, mutableListOf(fijo, viajante, comercioCorresponsal))
            viajante.shouldBe(centroDeDistribucion.vendedorEstrella())
        }
    }

    describe("sePuedeCubrir") {
        val fijo = Fijo(villaBosch)
        var comercioCorresponsal = ComercioCorresponsal(mutableListOf(villaBallester,gualeguay))
        val centroDeDistribucion = CentroDeDistribucion(villaBosch, mutableListOf(fijo, comercioCorresponsal))
        it("Se puede cubrir"){
            centroDeDistribucion.sePuedeCubrir(villaBosch).shouldBeTrue()
        }
        it("No se puede cubrir"){
            centroDeDistribucion.sePuedeCubrir(concepcionDelUruguay).shouldBeFalse()
        }
    }

    describe("vendedoresGenericos") {
        val fijo = Fijo(villaBosch)
        val viajante = Viajante(listOf(buenosAires, entreRios))
        var comercioCorresponsal = ComercioCorresponsal(mutableListOf(villaBosch, villaBallester,gualeguay, concepcionDelUruguay))
        it ("Dos son vendedores genericos") {
            fijo.agregarCertificacion(Certificacion(false,8))
            fijo.agregarCertificacion(Certificacion(true,9))
            viajante.agregarCertificacion(Certificacion(false,7))
            comercioCorresponsal.agregarCertificacion(Certificacion(true,5))
            val centroDeDistribucion = CentroDeDistribucion(villaBosch, mutableListOf(fijo, viajante, comercioCorresponsal))
            centroDeDistribucion.vendedoresGenericos().size.shouldBe(2)
            centroDeDistribucion.vendedoresGenericos().contains(fijo).shouldBeTrue()
            centroDeDistribucion.vendedoresGenericos().contains(viajante).shouldBeFalse()
            centroDeDistribucion.vendedoresGenericos().contains(comercioCorresponsal).shouldBeTrue()
        }
        it ("Ninguno es vendedor generico") {
            fijo.agregarCertificacion(Certificacion(false,8))
            viajante.agregarCertificacion(Certificacion(false,7))
            comercioCorresponsal.agregarCertificacion(Certificacion(false,5))
            val centroDeDistribucion = CentroDeDistribucion(villaBosch, mutableListOf(fijo, viajante, comercioCorresponsal))
            centroDeDistribucion.vendedoresGenericos().size.shouldBe(0)
            centroDeDistribucion.vendedoresGenericos().contains(fijo).shouldBeFalse()
            centroDeDistribucion.vendedoresGenericos().contains(viajante).shouldBeFalse()
            centroDeDistribucion.vendedoresGenericos().contains(comercioCorresponsal).shouldBeFalse()
        }
    }

    describe("esRobusto") {
        val fijo = Fijo(villaBosch)
        val viajante = Viajante(listOf(buenosAires, entreRios))
        var comercioCorresponsal = ComercioCorresponsal(mutableListOf(villaBosch, villaBallester,gualeguay, concepcionDelUruguay))
        it ("El centro es robusto") {
            fijo.agregarCertificacion(Certificacion(false,23))
            fijo.agregarCertificacion(Certificacion(true,22))
            viajante.agregarCertificacion(Certificacion(false,34))
            comercioCorresponsal.agregarCertificacion(Certificacion(true,31))
            val centroDeDistribucion = CentroDeDistribucion(villaBosch, mutableListOf(fijo, viajante, comercioCorresponsal))
            centroDeDistribucion.esRobusto().shouldBeTrue()
        }
        it ("El centro no es robusto") {
            fijo.agregarCertificacion(Certificacion(false,32))
            fijo.agregarCertificacion(Certificacion(true,9))
            viajante.agregarCertificacion(Certificacion(false,34))
            comercioCorresponsal.agregarCertificacion(Certificacion(true,5))
            val centroDeDistribucion = CentroDeDistribucion(villaBosch, mutableListOf(fijo, viajante, comercioCorresponsal))
            centroDeDistribucion.esRobusto().shouldBeFalse()
        }
    }
})