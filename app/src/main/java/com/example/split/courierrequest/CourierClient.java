package com.example.split.courierrequest;

import com.google.gson.Gson;
import models.SendRequestBody;
import models.SendResponseBody;
import services.Courier;
import services.SendService;

import java.io.IOException;
import java.util.Map;

public class CourierClient {

    // Events you can send are "user-signup", "user-joined", and "user-left".
    public static void send(String event, String email, String username,
                            String firstname)
            throws IOException {
        Courier.init("pk_prod_E8B471H2YH45P1MRVRW251DG7VSN");

        SendRequestBody request = new SendRequestBody();
        request.setEvent(event);
        request.setRecipient(email);

        Gson gson = new Gson(); // Convert Java Objects into JSON and back

        Map profileMap = gson.fromJson(
                "{'email': '" + email + "'}", Map.class);
        request.setProfile(new Gson().toJson(profileMap));

        Map dataMap = gson.fromJson(
                "{'username': '" + username + "',\r\n" +
                        "'firstname': '" + firstname + "'}", Map.class);
        request.setData(new Gson().toJson(dataMap));

        SendResponseBody response = new SendService().send(request);
        // System.out.println(response);
    }
}