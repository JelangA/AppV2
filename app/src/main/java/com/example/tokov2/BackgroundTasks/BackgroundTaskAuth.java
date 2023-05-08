package com.example.tokov2.BackgroundTasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.tokov2.Activity.MenuActivity;
import com.example.tokov2.util.AppConst;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class BackgroundTaskAuth extends AsyncTask<String, String, String> {

    Context context;

    public BackgroundTaskAuth(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];


        if (type.equals("login")){
            String username = params[1];
            String password = params[2];

            try {
                URL url = new URL(AppConst.URL_LOGIN);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);

                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                String urlencoder = URLEncoder.encode("email", "UTF-8") + "=" +
                        URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" +
                        URLEncoder.encode(password, "UTF-8");


                bufferedWriter.write(urlencoder);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();

                InputStream is = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "ISO-8859-1"));
                StringBuilder sb = new StringBuilder();

                String response = "";
                while ((response = bufferedReader.readLine()) != null) {
                    sb.append(response).append("\n");
                }


                JSONObject json = new JSONObject(sb.toString());
                response = json.getString("token");

                is.close();
                bufferedReader.close();
                httpURLConnection.disconnect();

                return response;

            } catch (ProtocolException e) {
                e.printStackTrace();
                return "error 1";
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "error 2";
            } catch (IOException e) {
                e.printStackTrace();
                return "Data not found";
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else if (type.equals("register")){
//            String nama_lengkap = params[1];
            String username = params[1];
//            String alamat = params[3];
            String password = params[2];

            try {
                URL url = new URL(AppConst.URL_REGISTER);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);

                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                String urlencoder =
//                        URLEncoder.encode("nama_lengkap", "UTF-8") + "=" +
//                        URLEncoder.encode(nama_lengkap, "UTF-8") + "&" +
                        URLEncoder.encode("username", "UTF-8") + "=" +
                        URLEncoder.encode(username, "UTF-8") + "&" +
//                        URLEncoder.encode("alamat", "UTF-8") + "=" +
//                        URLEncoder.encode(alamat, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" +
                        URLEncoder.encode(password, "UTF-8");


                bufferedWriter.write(urlencoder);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();

                InputStream is = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "ISO-8859-1"));
                StringBuilder sb = new StringBuilder();

                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line).append("\n");
                }

                JSONObject json = new JSONObject(sb.toString());
                result = json.getString("token");

                is.close();
                bufferedReader.close();
                httpURLConnection.disconnect();

                return result;

            } catch (ProtocolException e) {
                e.printStackTrace();
                return "protocol error";
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "url error";
            } catch (IOException e) {
                e.printStackTrace();
                return "Data not found";
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return "Task Gagal";
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if (result.contains("Data not found")){
            Toast.makeText(context, "Username atau Password Salah", Toast.LENGTH_LONG).show();
        }else {
            Intent intent = new Intent(context, MenuActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            Toast.makeText(context, "Authentifikasi Berhasil", Toast.LENGTH_SHORT).show();
        }
    }
}