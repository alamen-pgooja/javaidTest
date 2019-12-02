package com.example.javedtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity {


    List<Employee> employees;
    List<EmployeeAssignments> employeeAssignments;
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        employeeAssignments = new ArrayList<>();
        employees = new ArrayList<>();
        getAllEmployees();
        getAllAssignments();
        backupAllEmployees();
        backupAllAssignments();
    }

   //*********************************get all Data form data DATABASE*******************************
    private void getAllEmployees() {
        getClient().create(APIInterface.class).getAllEmploys().enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                Log.v(TAG, response.body().toString());
                employees=response.body();

            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void getAllAssignments() {
        getClient().create(APIInterface.class).getEmployeeAssignments().enqueue(new Callback<List<EmployeeAssignments>>() {
            @Override
            public void onResponse(Call<List<EmployeeAssignments>> call, Response<List<EmployeeAssignments>> response) {
                Log.i(TAG, response.body().toString());
                employeeAssignments=response.body();
                Log.i(TAG, "as json" + new JsonParser().parse(response.body().toString()).toString());
            }

            @Override
            public void onFailure(Call<List<EmployeeAssignments>> call, Throwable t) {

            }
        });
    }
    //*********************************get all Data form data DATABASE******************************



    //*********************************BACKUP all data TO copy table DATABASE***********************
    private void backupAllEmployees() {
        getClient().create(APIInterface.class).copyAllEmps(employees.toString()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i(TAG,response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }

    private void backupAllAssignments() {
        getClient().create(APIInterface.class).copyAllEmpsAssimants(employeeAssignments.toString()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i(TAG,response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
    //*********************************BACKUP all data TO copy table DATABASE***********************


    // API client response for network and http calling request and response
    static Retrofit getClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.MINUTES)
                .writeTimeout(60, TimeUnit.MINUTES)
                .addInterceptor(logging)
                .build();
        Gson gson = new GsonBuilder()
                .setLenient().setLenient()
                .create();
        return new Retrofit.
                Builder()
                .client(okHttpClient)
                .baseUrl("http://10.0.2.2/company/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }


    interface APIInterface {
        @GET("getAllEmps.php")
        Call<List<Employee>> getAllEmploys();

        @GET("getAllEmpsAssimants.php")
        Call<List<EmployeeAssignments>> getEmployeeAssignments();

        @FormUrlEncoded
        @POST("copyAllEmpsAssimants.php")
        Call<String> copyAllEmpsAssimants(@Field("values") String jsonObject);

        @FormUrlEncoded
        @POST("copyAllEmps.php")
        Call<String> copyAllEmps(@Field("values") String jsonObject);
    }

    //*************************Models That represent tables*****************************************
    class EmployeeAssignments {
        @SerializedName("employee_id")
        private String empId;
        @SerializedName("assignment_name")
        private String empAssignment;
        @SerializedName("assignment_description")
        private String empAssignmentDescription;
        @SerializedName("start_date")
        private String startDate;
        @SerializedName("end_date")
        private String endDate;

        public String getEmpId() {
            return empId;
        }

        public void setEmpId(String empId) {
            this.empId = empId;
        }

        public String getEmpAssignment() {
            return empAssignment;
        }

        public void setEmpAssignment(String empAssignment) {
            this.empAssignment = empAssignment;
        }

        public String getEmpAssignmentDescription() {
            return empAssignmentDescription;
        }

        public void setEmpAssignmentDescription(String empAssignmentDescription) {
            this.empAssignmentDescription = empAssignmentDescription;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        @Override
        public String toString() {
            return "{" +
                    "empId='" + empId + '\'' +
                    ", empAssignment='" + empAssignment + '\'' +
                    ", empAssignmentDescription='" + empAssignmentDescription + '\'' +
                    ", startDate='" + startDate + '\'' +
                    ", endDate='" + endDate + '\'' +
                    '}';
        }
    }

    class Employee {
        @SerializedName("employee_name")
        private String empName;
        @SerializedName("employee_id")
        private String employeeId;
        @SerializedName("employee_national_id_no")
        private String employeeNationalIdNo;
        @SerializedName("employee_photograph")
        private String employeePhoto;

        public String getEmpName() {
            return empName;
        }

        public void setEmpName(String empName) {
            this.empName = empName;
        }

        public String getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(String employeeId) {
            this.employeeId = employeeId;
        }


        public String getEmployeeNationalIdNo() {
            return employeeNationalIdNo;
        }

        public void setEmployeeNationalIdNo(String employeeNationalIdNo) {
            this.employeeNationalIdNo = employeeNationalIdNo;
        }

        public String getEmployeePhoto() {
            return employeePhoto;
        }

        public void setEmployeePhoto(String employeePhoto) {
            this.employeePhoto = employeePhoto;
        }

        @Override
        public String toString() {
            return "{" +
                    "empName='" + empName + '\'' +
                    ", employeeId='" + employeeId + '\'' +
                    ", employeeNationalIdNo='" + employeeNationalIdNo + '\'' +
                    ", employeePhoto='" + employeePhoto + '\'' +
                    '}';
        }
    }
//*************************Models That represent tables*****************************************
}

