package com.risjsnx.v4aadvanced

import android.net.LocalSocket
import android.net.LocalSocketAddress

object V4AInterface {

    private fun send(cmd: String): String? {
        return try {
            val socket = LocalSocket()
            socket.connect(
                LocalSocketAddress(
                    "v4a_public_interface",
                    LocalSocketAddress.Namespace.FILESYSTEM
                )
            )

            socket.outputStream.write((cmd + "\n").toByteArray())
            socket.outputStream.flush()

            val res = socket.inputStream
                .bufferedReader()
                .readLine()

            socket.close()
            res
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun isInstalled(): Boolean {
        return send("status") == "installed"
    }

    fun openApp(): Boolean {
        return send("open_app") == "opened"
    }

    fun getVersion(): String? {
        return send("version")
    }
}
