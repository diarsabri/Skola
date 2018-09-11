
/*
(a) Write a statement that preforms the creation of records recording the details of patients referred to the outpatient clinic (Charge Nurse). Which also enforces constrains on drug identification numbers.
*/

CREATE TABLE patient_medication_form (
    patient_id integer REFERENCES patient(patient_id) NOT NULL,
    full_name varchar(50) NOT NULL,
    ward_id integer REFERENCES ward(ward_id) NOT NULL,
    ward_name varchar(50) NOT NULL,
    bed_id integer NOT NULL,
    drug_id integer REFERENCES drug(drug_id) NOT NULL,
    drug_name varchar(50) NOT NULL,
    description varchar(50) NOT NULL,
    dosage integer NOT NULL,
    method_of_admin varchar(50) NOT NULL,
    units_per_day integer NOT NULL,
    start date NOT NULL,
    finish date NOT NULL
);

SELECT * FROM patient_medication_form 
INNER JOIN patient ON patient.patient_id = patient_medication_form.patient_id
WHERE ward_id = XX -- XX is the number of the outpatient clinic



/*
(b) Write a query that allows the Charge Nurse in obtaining surgical,nonsurgical, and pharmaceutical supplies from the central stock of supplies held by the hospital taking into consideration the information detailed on a requisition, which should also enforce
necessary constrains.
*/

INSERT INTO requisition_form VALUES (
    1,
    12,
    'NAMN1',
    2017-05-25,
    412,
    'NAMN2',
    2017-05-29
);

/*
(c) Write query generating a report listing the details of patients currently on the waiting list for a particular ward (Charge Nurse and Medical Director).
*/

SELECT patient_id, firstName, lastName, date_appointment, required_ward, 
expected_stay, datediff(CURRENT_DATE-Date_On_Waiting_List) AS Days_on_waiting_list 
FROM Patient WHERE Patient_Type = 'Inpatient'
ORDER BY Days_on_waiting_list DESC;


/*
(d) Write a constraint which disallows modification if it does not satisfy the stated constraint when a new patient enters the ward.
*/

ALTER TABLE patient ADD CONSTRAINT type_allowed 
CHECK (patient_type = 'Inpatient' OR patient_type = 'Outpatient' );

/*
(e) Write a constraint which disallows the modification if it does not satisfy the stated constraint when suppliers are added to the database.
*/

ALTER TABLE supplier ADD CONSTRAINT type_allowed 
ADD PRIMARY KEY (supplier_id);


/*
(f) Identify user views for the Medical Director and Charge Nurse in the Northshore Hospital case study. Provide a users’ requirements specification for each of these user views. State any assumptions you would make.
*/



/*
(g) Identify the views for the Personnel Officer, who is responsible for ensuring that the appropriate number and type of staff are allocated to each ward and the outpatient clinic. Provide a users’ requirements specification for each of these views. State any assumptions you would make.
*/

