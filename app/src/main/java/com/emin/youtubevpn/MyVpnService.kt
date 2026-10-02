package com.emin.youtubevpn

import android.content.Intent
import android.net.VpnService
import android.os.ParcelFileDescriptor

/**
 * IMPORTANT:
 * This is only the Android VpnService shell.
 * It does NOT provide Internet tunneling by itself.
 *
 * To make a working VPN, integrate a VPN engine (for example libbox)
 * and provide a valid upstream server/profile.
 */
class MyVpnService : VpnService() {

    private var tun: ParcelFileDescriptor? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Deliberately not starting a fake tunnel.
        // A fake TUN interface would make the app appear connected
        // while traffic would not reach the Internet.
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        tun?.close()
        tun = null
        super.onDestroy()
    }
}
