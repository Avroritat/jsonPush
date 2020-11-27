package com.example.jsonpush;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private JSONArray createJSON() throws JSONException {
        ArrayList<JSONObject> jsonArrayList = new ArrayList<>();
        JSONObject object;
        /*** Ряд 1 ***/
        object = new JSONObject();
        object.put("MemberID", "1");
        object.put("Name", "Лолита Гоперштейн");
        object.put("Tel", "88005553535");
        jsonArrayList.add(object);
        /*** Ряд 2 ***/
        object = new JSONObject();
        object.put("MemberID", "2");
        object.put("Name", "Максим Абраменко");
        object.put("Tel", "89241763435");
        jsonArrayList.add(object);
        /*** Ряд 3 ***/
        object = new JSONObject();
        object.put("MemberID", "3");
        object.put("Name", "Никита Додиев");
        object.put("Tel", "89142406470");
        jsonArrayList.add(object);
        JSONArray jsonArray = new JSONArray(jsonArrayList);
        return jsonArray;
    }

    public void onClick(View view) {
        ListView listView = findViewById(R.id.listView);
        try {
            JSONArray data = createJSON();
            ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
            HashMap<String, String> hashMap;
            for (int i = 0; i < data.length(); i++) {
                JSONObject jsonObject = data.getJSONObject(i);
                hashMap = new HashMap<>();
                hashMap.put("MemberID", jsonObject.getString("MemberID"));
                hashMap.put("Name", jsonObject.getString("Name"));
                hashMap.put("Tel", jsonObject.getString("Tel"));
                arrayList.add(hashMap);
            }
            SimpleAdapter simpleAdapter;
            simpleAdapter = new SimpleAdapter(this, arrayList,
                    R.layout.list_item, new String[]{"MemberID",
                    "Name", "Tel"}, new int[]{R.id.item_textViewMemberID,
                    R.id.item_textViewName, R.id.item_textViewNumber});
            listView.setAdapter(simpleAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}