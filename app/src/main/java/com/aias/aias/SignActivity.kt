package com.aias.aias

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.github.kittinunf.fuel.Fuel
import kotlin.concurrent.thread


class SignActivity : AppCompatActivity(), View.OnClickListener {
    val publicKey = """-----BEGIN PUBLIC KEY-----
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxXo2zWkciUEZBcm/Exk8
Zac8NWskP59EAVFlO218xIXOV0FfphPB/tnbQh7GDXddo7XVEptHdHXyJlXXLihb
9vXbUZF2NDFLOhgDv7pa72VNLbw+jKR/FlsDtwv/bv7ZDqq+n79uavuJ8giX3qCf
+mtBmro7hG5AVve3JImhvA0FvTKJ0xCYUYw02st08He5RwFAXQK8G2cwahp+5ECH
MDdfFUaoxMfRN/+Hl9iqiJovKUJQ3545N2fDYdd0eqSlqL1N5xJxYX1GDMtGZgME
hHR6ntdfm7r43HDB4hk/MJIsNay6+K9tJBiz1qXG40G4NjMKzVrX9pi1Bv8G2RnP
/wIDAQAB
-----END PUBLIC KEY-----"""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone)


        AlertDialog.Builder(this)
            .setTitle("warning")
            .setMessage("Are you trust calling app?")
            .setPositiveButton("OK") { dialog, which ->

            }
            .setNegativeButton("Cancel", { dialogInterface: DialogInterface, i: Int ->
                finish()
            })
            .show()
    }

    override fun onClick(v: View?) {
        when (v!!.getId()) {
            R.id.submit_phone -> {
                setContentView(R.layout.activity_sms_code)
            }
        }

        val text = intent.getStringExtra("message")

        thread {
            Aias.new(publicKey, publicKey);

            val blindedDigest = Aias.blind(text);

            val (_, readyResponse, _) = Fuel.post("http://192.168.0.24:8080/ready")
                .body(blindedDigest!!)
                .response()

            val subset = String(readyResponse.data)
            Aias.setSubset(subset)

            val checkParam = Aias.generateCheckParameter();

            val (_, signResponse, _) = Fuel.post("http://192.168.0.24:8080/sign")
                .body(checkParam!!)
                .response()

            val blindSignature = String(signResponse.data)
            val signature = Aias.unblind(blindSignature)

            runOnUiThread {
                Toast.makeText(this, signature, Toast.LENGTH_LONG).show();
            }
        }
    }

}