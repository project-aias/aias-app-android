package com.aias.aias

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.aias.aias.Aias

class MainActivity : AppCompatActivity() {
    val publicKey = """-----BEGIN PUBLIC KEY-----
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxXo2zWkciUEZBcm/Exk8
Zac8NWskP59EAVFlO218xIXOV0FfphPB/tnbQh7GDXddo7XVEptHdHXyJlXXLihb
9vXbUZF2NDFLOhgDv7pa72VNLbw+jKR/FlsDtwv/bv7ZDqq+n79uavuJ8giX3qCf
+mtBmro7hG5AVve3JImhvA0FvTKJ0xCYUYw02st08He5RwFAXQK8G2cwahp+5ECH
MDdfFUaoxMfRN/+Hl9iqiJovKUJQ3545N2fDYdd0eqSlqL1N5xJxYX1GDMtGZgME
hHR6ntdfm7r43HDB4hk/MJIsNay6+K9tJBiz1qXG40G4NjMKzVrX9pi1Bv8G2RnP
/wIDAQAB
-----END PUBLIC KEY-----
"""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Aias.new(publicKey);
        Toast.makeText(this, publicKey, Toast.LENGTH_SHORT).show();
    }
}