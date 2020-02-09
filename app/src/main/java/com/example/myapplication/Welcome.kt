package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.myapplication.mqtt.MqttClientHelper
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_welcome.*
import kotlinx.android.synthetic.main.content_main.*
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended
import org.eclipse.paho.client.mqttv3.MqttException
import org.eclipse.paho.client.mqttv3.MqttMessage
import java.util.*
import kotlin.concurrent.schedule

class Welcome : AppCompatActivity() {

    var stockNames = "Hello People";

    companion object {
        const val TAG = "Welcome"

        // Define these values in res/values/strings.xml
        //const val TOPIC = "my/first/topic/name"
        const val TOPIC = "/drugs"
        const val MSG = "My string message payload"
    }

    val mqttClient by lazy {
        MqttClientHelper(this)
    }


    private fun setMqttCallBack() {
        mqttClient.setCallback(object : MqttCallbackExtended {
            override fun connectComplete(b: Boolean, s: String) {
                Log.w("Debug", "Connected to host '$SOLACE_MQTT_HOST'.")
            }

            override fun connectionLost(throwable: Throwable) {
                Log.w("Debug", "Connected to host '$SOLACE_MQTT_HOST' lost.")
            }

            @Throws(Exception::class)
            override fun messageArrived(topic: String, mqttMessage: MqttMessage) {
                Log.w("Debug", "Message received from host '$SOLACE_MQTT_HOST': $mqttMessage")
                //EditText1.setText("${EditText1.text.toString().toInt() + 1}")
                stockNames = "" + mqttMessage;
                Toast.makeText(
                    baseContext,
                    "Hello: " + topic + "World: " + mqttMessage,
                    Toast.LENGTH_SHORT
                )
                    .show()

                Toast.makeText(
                    baseContext,
                    "List: " + stockNames,
                    Toast.LENGTH_SHORT
                )
                    .show()

            }

            override fun deliveryComplete(iMqttDeliveryToken: IMqttDeliveryToken) {
                Log.w("Debug", "Message published to host '$SOLACE_MQTT_HOST'")
            }
        })
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        //super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        //setSupportActionBar(toolbar)


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        setMqttCallBack()


        val button = findViewById<View>(R.id.buttonGo) as Button
        button.setOnClickListener { v ->
            val intent = Intent(v.context, ForSale::class.java)

            var snackbarMsg: String
            try {
                mqttClient.subscribe(Welcome.TOPIC)
                snackbarMsg = "Subscribed to topic '${Welcome.TOPIC}'!"
            } catch (ex: MqttException) {
                snackbarMsg = "Error subscribing to topic: ${Welcome.TOPIC}!"

            }
            //Snackbar.make(v, snackbarMsg, Snackbar.LENGTH_LONG).setAction("Action", null).show()


            Thread.sleep(1000);
            intent.putExtra("stockNames", stockNames);

            startActivity(intent)
        }

        Timer("CheckMqttConnection", false).schedule(3000) {
            if (!mqttClient.isConnected()) {
//                Snackbar.make(
//                    buttonGo,
//                    "Failed to connect to: '$SOLACE_MQTT_HOST' within 3 seconds",
//                    Snackbar.LENGTH_INDEFINITE
//                )
//                    .setAction("Action", null).show()
            }
        }


    }


}

