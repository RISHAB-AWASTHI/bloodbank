package com.example.bloodbank.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bloodbank.R;

public class registerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);
    nameEt = findViewById(R.id.name);
    cityEt = findViewById(R.id.city);
    bloodGroupEt = findViewById(R.id.blood_group);
    passwordEt = findViewById(R.id.password);
    mobileEt = findViewById(R.id.number);
    submitButton = findViewById(R.id.submit_button);
    submitButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        String name, city, blood_group, password, mobile;
        name = nameEt.getText().toString();
        city = cityEt.getText().toString();
        blood_group = bloodGroupEt.getText().toString();
        password = passwordEt.getText().toString();
        mobile = mobileEt.getText().toString();
        if (isValid(name, city, blood_group, password, mobile)) {
          register(name, city, blood_group, password, mobile);
        }
      }
    });

  }
private void register(final String name, final String city, final String blood_group, final String password,
      final String mobile) {
    StringRequest stringRequest = new StringRequest(Method.POST, Endpoints.register_url, new Listener<String>() {
      @Override
      public void onResponse(String response) {
        if(response.equals("Success")){
          PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit()
              .putString("city", city).apply();
          Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
          startActivity(new Intent(RegisterActivity.this, MainActivity.class));
          RegisterActivity.this.finish();
        }else{
          Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
        }
      }
    }
private boolean isValid(String name, String city, String blood_group, String password,
      String mobile) {
    List<String> valid_blood_groups = new ArrayList<>();
    valid_blood_groups.add("A+");
    valid_blood_groups.add("A-");
    valid_blood_groups.add("B+");
    valid_blood_groups.add("B-");
    valid_blood_groups.add("AB+");
    valid_blood_groups.add("AB-");
    valid_blood_groups.add("O+");
    valid_blood_groups.add("O-");
    if (name.isEmpty()) {
      showMessage("Name is empty");
      return false;
    } else if (city.isEmpty()) {
      showMessage("City name is required");
      return false;
    } else if (!valid_blood_groups.contains(blood_group)) {
      showMessage("Blood group invalid choose from " + valid_blood_groups);
      return false;
    } else if (mobile.length() != 10) {
      showMessage("Invalid mobile number, number should be of 10 digits");
      return false;
    }
    return true;
  }


  private void showMessage(String msg) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
  }


}
