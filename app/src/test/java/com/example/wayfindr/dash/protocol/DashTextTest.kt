package com.example.wayfindr.dash.protocol

import org.junit.Assert.assertArrayEquals
import org.junit.Test

class DashTextTest {
    private fun latin1(s: String) = s.toByteArray(Charsets.ISO_8859_1)

    @Test fun turkishLettersFitTheDashFont() {
        assertArrayEquals(latin1("XFITness"), DashCommands.dashText("XFİTness"))
        assertArrayEquals(latin1("Kizilay Isik Sisli Dagi"), DashCommands.dashText("Kızılay Işık Şişli Dağı"))
        assertArrayEquals(latin1("Gölbaşı".replace("ş", "s").replace("ı", "i")), DashCommands.dashText("Gölbaşı"))
        assertArrayEquals(latin1("Çankaya Üreğil".replace("ğ", "g")), DashCommands.dashText("Çankaya Üreğil"))
    }
}
