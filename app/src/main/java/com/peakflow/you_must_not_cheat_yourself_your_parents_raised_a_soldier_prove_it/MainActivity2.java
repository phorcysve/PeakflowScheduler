package com.peakflow.you_must_not_cheat_yourself_your_parents_raised_a_soldier_prove_it;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {

    private static final String API_KEY = "AIzaSyCeqyf_XFT3dkXHUGb5z3zzanSVArCO51g"; // API Key
    private static final String URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateText?key=" + API_KEY;
    private TextView responseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }


    // adding the method to send prompt and get response



    private void sendPrompt(String prompt) {
        OkHttpClient client = new OkHttpClient();

        // JSON request body
        String json = "{ \"prompt\": { \"text\": \"" + prompt + "\" } }";
        RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));

        // Create request
        Request request = new Request.Builder()
                .url(URL)
                .post(body)
                .build();

        // Make API call asynchronously
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("API_ERROR", "Failed to fetch response", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                JSONObject jsonResponse;
                JSONArray candidates;
                String outputText= "";
                if (!response.isSuccessful()) {
                    Log.e("API_ERROR", "Unexpected response: " + response);
                }
                String responseData = response.body().string();
                try {
                    jsonResponse = new JSONObject(responseData);
                    candidates = jsonResponse.getJSONArray("candidates");
                    outputText = candidates.getJSONObject(0).getString("output");

                    // Update UI with response
                    String finalOutputText = outputText;
                    runOnUiThread(() -> responseTextView.setText(finalOutputText));


                } catch (Exception e) {
                    Log.e("JSON_ERROR", "Failed to parse response", e);
                }

            }
        });

    }

    String getResponse(String s){
        return s;
    }
}