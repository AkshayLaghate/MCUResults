package com.ClassyCreations.mcuresults;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    ImageView ivDp;
    TextView tvName, tvResult, tvMarks;
    String examid, rollno, name, result, marks;

    TextView tvSubject1, tvTheory1, tvPractical1, tvSessional1, tvTotal1,
            tvSubject2, tvTheory2, tvPractical2, tvSessional2, tvTotal2,
            tvSubject3, tvTheory3, tvPractical3, tvSessional3, tvTotal3,
            tvSubject4, tvTheory4, tvPractical4, tvSessional4, tvTotal4;

    ArrayList<String> subjects, theory, practical, sessional, total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle bag = getIntent().getExtras();

        Log.d("MCU", "onCreate: " + bag.getString("roll") + bag.getString("exam"));
        examid = bag.getString("exam");
        rollno = bag.getString("roll");

        ivDp = (ImageView) findViewById(R.id.ivDP);
        tvName = (TextView) findViewById(R.id.tvName);
        tvResult = (TextView) findViewById(R.id.tvResult);
        tvMarks = (TextView) findViewById(R.id.tvMarks);

        tvSubject1 = (TextView) findViewById(R.id.tvSubject1);
        tvSubject2 = (TextView) findViewById(R.id.tvSubject2);
        tvSubject3 = (TextView) findViewById(R.id.tvSubject3);
        tvSubject4 = (TextView) findViewById(R.id.tvSubject4);
        tvTheory1 = (TextView) findViewById(R.id.tvTheory1);
        tvTheory2 = (TextView) findViewById(R.id.tvTheory2);
        tvTheory3 = (TextView) findViewById(R.id.tvTheory3);
        tvTheory4 = (TextView) findViewById(R.id.tvTheory4);
        tvPractical1 = (TextView) findViewById(R.id.tvParctical1);
        tvPractical2 = (TextView) findViewById(R.id.tvParctical2);
        tvPractical3 = (TextView) findViewById(R.id.tvParctical3);
        tvPractical4 = (TextView) findViewById(R.id.tvParctical4);
        tvSessional1 = (TextView) findViewById(R.id.tvSessional1);
        tvSessional2 = (TextView) findViewById(R.id.tvSessional2);
        tvSessional3 = (TextView) findViewById(R.id.tvSessional3);
        tvSessional4 = (TextView) findViewById(R.id.tvSessional4);
        tvTotal1 = (TextView) findViewById(R.id.tvTotal1);
        tvTotal2 = (TextView) findViewById(R.id.tvTotal2);
        tvTotal3 = (TextView) findViewById(R.id.tvTotal3);
        tvTotal4 = (TextView) findViewById(R.id.tvTotal4);

        subjects = new ArrayList<>();
        theory = new ArrayList<>();
        practical = new ArrayList<>();
        sessional = new ArrayList<>();
        total = new ArrayList<>();


        new FetchResult().execute();

    }

    private void fetchResult() {

        try {
            Document doc = Jsoup.connect("http://103.21.54.52/mlcvresult/SearchResult.aspx?groupind=3&Revel=0")
                    .data("DDlExamName", examid)
                    .data("Submit", "Submit")
                    .data("TxtRollno", rollno)
                    .data("__EVENTVALIDATION", "/wEdAAtLTY5uFRBChculhkue7ooaTRvSo5XDpusmP/haWwwtgA3R0Mr8IyKD1Ki91vXeeWiv5xUFfyMWOqO+gJSY0Svg2vrEZcL+5vl7iOiuBcWtJTyiO12HGIEj5p7WGMCKpRH2dlbGMsaLpVhilcifTQyusrcz25gDAHBlnFPArp8El+hZM01UQ3/q9mvnZpGwLu53wFxYYfWi3STRI7sJ+66Upd+RSPlczgML7lqST15A2A5r8aBnkSWoDvECKzI3mz0Ijs05sJYL5VdR/gQU+STT")
                    .data("__VIEWSTATE", "/wEPDwUJNzgxNzY5MjQwD2QWAgIDD2QWBAIFDw8WAh4EVGV4dAUpUGxlYXNlIFNlbGVjdCBOYW1lIG9mwqBUaGUgRXhhbSBGcm9tIExpc3RkZAIJDxAPFgYeDURhdGFUZXh0RmllbGQFCEVYQU1OQU1FHg5EYXRhVmFsdWVGaWVsZAUEQ1RSTB4LXyFEYXRhQm91bmRnZBAVBhAtLS0tLVNlbGVjdC0tLS0tL01BU1RFUiBPRiBDT01QVVRFUiBBUFBMSUNBVElPTlMtIE1DQShMRSkgVkkgU0VNL01BU1RFUiBPRiBDT01QVVRFUiBBUFBMSUNBVElPTlMtIE1DQShMRSkgSVYgU0VNLk1BU1RFUiBPRiBDT01QVVRFUiBBUFBMSUNBVElPTlMtIE1DQShMRSkgViBTRU0wQkFDSEVMT1IgT0YgQ09NUFVURVIgQVBQTElDQVRJT04gKEIuQy5BLikgVkktU0VNME1BU1RFUiBPRiBDT01QVVRFUiBBUFBMSUNBVElPTlMtIE1DQShMRSkgSUlJIFNFTRUGATAENjAyNgQ2MDI0BDYwMjUGNTAxNjAzBDYwMjMUKwMGZ2dnZ2dnFgFmZGQzl8FPW66klKkklA/TqP26Alw9HmIWEn7WpcP0sciB9w==")
                    .post();

            final Element img = doc.getElementById("Image1");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Picasso.with(getApplicationContext()).load(img.attr("src")).into(ivDp);
                }
            });

            Log.d("MCU", "fetchResult: " + img.attr("src"));
            Log.d("MCU", "fetchResult: " + doc.toString());

            Element name = doc.getElementById("LblName");
            tvName.setText(name.text());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private class FetchResult extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            try {
                Document doc = Jsoup.connect("http://103.21.54.52/mlcvresult/SearchResult.aspx?groupind=3&Revel=0")
                        .data("DDlExamName", examid)
                        .data("Submit", "Submit")
                        .data("TxtRollno", rollno)
                        .data("__EVENTVALIDATION", "/wEdAAtLTY5uFRBChculhkue7ooaTRvSo5XDpusmP/haWwwtgA3R0Mr8IyKD1Ki91vXeeWiv5xUFfyMWOqO+gJSY0Svg2vrEZcL+5vl7iOiuBcWtJTyiO12HGIEj5p7WGMCKpRH2dlbGMsaLpVhilcifTQyusrcz25gDAHBlnFPArp8El+hZM01UQ3/q9mvnZpGwLu53wFxYYfWi3STRI7sJ+66Upd+RSPlczgML7lqST15A2A5r8aBnkSWoDvECKzI3mz0Ijs05sJYL5VdR/gQU+STT")
                        .data("__VIEWSTATE", "/wEPDwUJNzgxNzY5MjQwD2QWAgIDD2QWBAIFDw8WAh4EVGV4dAUpUGxlYXNlIFNlbGVjdCBOYW1lIG9mwqBUaGUgRXhhbSBGcm9tIExpc3RkZAIJDxAPFgYeDURhdGFUZXh0RmllbGQFCEVYQU1OQU1FHg5EYXRhVmFsdWVGaWVsZAUEQ1RSTB4LXyFEYXRhQm91bmRnZBAVBhAtLS0tLVNlbGVjdC0tLS0tL01BU1RFUiBPRiBDT01QVVRFUiBBUFBMSUNBVElPTlMtIE1DQShMRSkgVkkgU0VNL01BU1RFUiBPRiBDT01QVVRFUiBBUFBMSUNBVElPTlMtIE1DQShMRSkgSVYgU0VNLk1BU1RFUiBPRiBDT01QVVRFUiBBUFBMSUNBVElPTlMtIE1DQShMRSkgViBTRU0wQkFDSEVMT1IgT0YgQ09NUFVURVIgQVBQTElDQVRJT04gKEIuQy5BLikgVkktU0VNME1BU1RFUiBPRiBDT01QVVRFUiBBUFBMSUNBVElPTlMtIE1DQShMRSkgSUlJIFNFTRUGATAENjAyNgQ2MDI0BDYwMjUGNTAxNjAzBDYwMjMUKwMGZ2dnZ2dnFgFmZGQzl8FPW66klKkklA/TqP26Alw9HmIWEn7WpcP0sciB9w==")
                        .post();

                final Element img = doc.getElementById("Image1");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Picasso.with(getApplicationContext()).load(img.attr("src")).into(ivDp);
                    }
                });

                Log.d("MCU", "fetchResult: " + img.attr("src"));
                Log.d("MCU", "fetchResult: " + doc.toString());

                Element names = doc.getElementById("LblName");
                name = names.text();

                Elements tdTot = doc.getElementsByClass("tdTot");

                marks = tdTot.get(0).text().replace("TOTAL MARKS-", "");
                result = tdTot.get(1).text().replace("RESULT-", "").replace("\u00a0", "");

                Element span = doc.getElementById("lblDetail");
                Elements trs = span.getElementsByTag("tr");

                for (int i = 1; i < (trs.size() - 1); i++) {
                    Elements tds = trs.get(i).getElementsByTag("td");
                    Log.d("MCU", "doInBackground: " + "Size :" + tds.size());
                    subjects.add(tds.get(0).text());
                    theory.add(tds.get(1).text());
                    practical.add(tds.get(2).text());
                    sessional.add(tds.get(3).text());
                    total.add(tds.get(4).text() + "/" + tds.get(5).text());
                }

                Log.d("MCU", "doInBackground: " + "Subject :" + trs.get(1).text());


            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            tvName.setText(name);
            tvMarks.setText(marks);
            tvResult.setText(result);

            if (result.contains("PASS")) {
                tvResult.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
            }


            if (subjects.size() > 0) {
                tvSubject1.setText(subjects.get(0));
                tvTheory1.setText("Theory : " + theory.get(0));
                tvPractical1.setText("Practical : " + practical.get(0));
                tvSessional1.setText("Sessional : " + sessional.get(0));
                tvTotal1.setText("Total : " + total.get(0));

                tvSubject2.setText(subjects.get(1));
                tvTheory2.setText("Theory : " + theory.get(1));
                tvPractical2.setText("Practical : " + practical.get(1));
                tvSessional2.setText("Sessional : " + sessional.get(1));
                tvTotal2.setText("Total : " + total.get(1));

                tvSubject3.setText(subjects.get(2));
                tvTheory3.setText("Theory : " + theory.get(2));
                tvPractical3.setText("Practical : " + practical.get(2));
                tvSessional3.setText("Sessional : " + sessional.get(2));
                tvTotal3.setText("Total : " + total.get(2));

                tvSubject4.setText(subjects.get(3));
                tvTheory4.setText("Theory : " + theory.get(3));
                tvPractical4.setText("Practical : " + practical.get(3));
                tvSessional4.setText("Sessional : " + sessional.get(3));
                tvTotal4.setText("Total : " + total.get(3));

            }
        }
    }
}
