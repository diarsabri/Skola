CREATE TABLE "Ward" (
	"ward_id" serial NOT NULL,
	"ward_name" serial NOT NULL,
	"location" serial NOT NULL,
	"number_of_beds" serial NOT NULL,
	"telephone" serial NOT NULL,
	CONSTRAINT Ward_pk PRIMARY KEY ("ward_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Patient" (
	"patient_id" BINARY NOT NULL,
	"first_name" BINARY NOT NULL,
	"last_name" BINARY NOT NULL,
	"address" BINARY NOT NULL,
	"date_of_birth" BINARY NOT NULL,
	"date_reg" BINARY NOT NULL,
	"telephone" BINARY NOT NULL,
	"marital_status" BINARY NOT NULL,
	"patient_type" BINARY NOT NULL,
	"gender" BINARY NOT NULL,
	"date_appointment" BINARY NOT NULL,
	"time_appointment" BINARY NOT NULL,
	"date_on_waiting_list" BINARY NOT NULL,
	"required_ward" BINARY NOT NULL,
	"expected_stay" BINARY NOT NULL,
	"placement_date" BINARY NOT NULL,
	"expected_leave" BINARY NOT NULL,
	"actual_leave" BINARY NOT NULL,
	"actual_stay" BINARY NOT NULL,
	"bed_id" BINARY NOT NULL,
	CONSTRAINT Patient_pk PRIMARY KEY ("patient_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Staff" (
	"staff_id" serial NOT NULL,
	"ward_id" serial NOT NULL,
	"fist_name" serial NOT NULL,
	"last_name" serial NOT NULL,
	"address" serial NOT NULL,
	"gender" serial NOT NULL,
	"date_of_birth" serial NOT NULL,
	"telephone" serial NOT NULL,
	"insurance_number" serial NOT NULL,
	"position" serial NOT NULL,
	"current_salary" serial NOT NULL,
	"salary_scale" serial NOT NULL,
	"hours_per_week" serial NOT NULL,
	"payment_form" serial NOT NULL,
	"employment_type" serial NOT NULL,
	CONSTRAINT Staff_pk PRIMARY KEY ("staff_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Patient_appointment" (
	"appointmend_id" serial NOT NULL,
	"ward_id" serial NOT NULL,
	"staff_id" serial NOT NULL,
	"patient_id" serial NOT NULL,
	"patient_type" serial NOT NULL,
	CONSTRAINT Patient_appointment_pk PRIMARY KEY ("appointmend_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Patient_medication" (
	"patient_id" BINARY NOT NULL,
	"ward_id" BINARY NOT NULL,
	"drug_id" BINARY NOT NULL,
	"start_date" BINARY NOT NULL,
	"finish_date" BINARY NOT NULL,
	"units_per_day" BINARY NOT NULL
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Pharmaceutical_supplies" (
	"drug_id" serial NOT NULL,
	"drug_name" serial NOT NULL,
	"description" serial NOT NULL,
	"dosage" serial NOT NULL,
	"method_of_admin" serial NOT NULL,
	"quantity_in_stock" serial NOT NULL,
	"reorder_level" serial NOT NULL,
	"cost_per_unit" serial NOT NULL,
	CONSTRAINT Pharmaceutical_supplies_pk PRIMARY KEY ("drug_id")
) WITH (
  OIDS=FALSE
);





ALTER TABLE "Staff" ADD CONSTRAINT "Staff_fk0" FOREIGN KEY ("ward_id") REFERENCES "Ward"("ward_id");
ALTER TABLE "Staff" ADD CONSTRAINT "Staff_fk1" FOREIGN KEY ("fist_name") REFERENCES "Patient"("");
ALTER TABLE "Staff" ADD CONSTRAINT "Staff_fk2" FOREIGN KEY ("last_name") REFERENCES "Patient"("");
ALTER TABLE "Staff" ADD CONSTRAINT "Staff_fk3" FOREIGN KEY ("address") REFERENCES "Patient"("");
ALTER TABLE "Staff" ADD CONSTRAINT "Staff_fk4" FOREIGN KEY ("gender") REFERENCES "Patient"("");
ALTER TABLE "Staff" ADD CONSTRAINT "Staff_fk5" FOREIGN KEY ("date_of_birth") REFERENCES "Patient"("");
ALTER TABLE "Staff" ADD CONSTRAINT "Staff_fk6" FOREIGN KEY ("telephone") REFERENCES "Patient"("");
ALTER TABLE "Staff" ADD CONSTRAINT "Staff_fk7" FOREIGN KEY ("insurance_number") REFERENCES "Patient"("");
ALTER TABLE "Staff" ADD CONSTRAINT "Staff_fk8" FOREIGN KEY ("position") REFERENCES "Patient"("");
ALTER TABLE "Staff" ADD CONSTRAINT "Staff_fk9" FOREIGN KEY ("current_salary") REFERENCES "Patient"("");
ALTER TABLE "Staff" ADD CONSTRAINT "Staff_fk10" FOREIGN KEY ("salary_scale") REFERENCES "Patient"("");
ALTER TABLE "Staff" ADD CONSTRAINT "Staff_fk11" FOREIGN KEY ("hours_per_week") REFERENCES "Patient"("");
ALTER TABLE "Staff" ADD CONSTRAINT "Staff_fk12" FOREIGN KEY ("payment_form") REFERENCES "Patient"("");
ALTER TABLE "Staff" ADD CONSTRAINT "Staff_fk13" FOREIGN KEY ("employment_type") REFERENCES "Patient"("");

ALTER TABLE "Patient_appointment" ADD CONSTRAINT "Patient_appointment_fk0" FOREIGN KEY ("ward_id") REFERENCES "Ward"("ward_id");
ALTER TABLE "Patient_appointment" ADD CONSTRAINT "Patient_appointment_fk1" FOREIGN KEY ("staff_id") REFERENCES "Staff"("staff_id");
ALTER TABLE "Patient_appointment" ADD CONSTRAINT "Patient_appointment_fk2" FOREIGN KEY ("patient_id") REFERENCES "Patient"("patient_id");

ALTER TABLE "Patient_medication" ADD CONSTRAINT "Patient_medication_fk0" FOREIGN KEY ("patient_id") REFERENCES "Patient"("patient_id");
ALTER TABLE "Patient_medication" ADD CONSTRAINT "Patient_medication_fk1" FOREIGN KEY ("ward_id") REFERENCES "Ward"("ward_id");
ALTER TABLE "Patient_medication" ADD CONSTRAINT "Patient_medication_fk2" FOREIGN KEY ("drug_id") REFERENCES "Pharmaceutical_supplies"("drug_id");


