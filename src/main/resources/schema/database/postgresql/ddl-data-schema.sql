CREATE TABLE "transfer_history"(
    "id" bigserial NOT NULL,
    "user_id" VARCHAR(255) NOT NULL,
    "debit_card" VARCHAR(255) NOT NULL,
    "recipient_card" VARCHAR(255) NOT NULL,
    "amount" DOUBLE PRECISION NOT NULL,
    "fee" DOUBLE PRECISION NOT NULL,
    "sender_full_name" VARCHAR(255) NOT NULL,
    "recipient_full_name" VARCHAR(255) NOT NULL,
    "created_date" DATE NOT NULL,
    "payment_date" DATE NOT NULL
);
ALTER TABLE
    "transfer_history" ADD PRIMARY KEY("id");
CREATE TABLE "region"(
    "id" SERIAL NOT NULL,
    "name" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "region" ADD PRIMARY KEY("id");
CREATE TABLE "registiration_temp_sent_code"(
    "id" BIGINT NOT NULL,
    "token" VARCHAR(255) NOT NULL,
    "sent_code" VARCHAR(255) NOT NULL,
    "expiration" DATE NOT NULL
);
ALTER TABLE
    "registiration_temp_sent_code" ADD PRIMARY KEY("id");
CREATE TABLE "users"(
    "id" VARCHAR(255) NOT NULL,
    "name" VARCHAR(255) NOT NULL,
    "phone" VARCHAR(255) NOT NULL,
    "password" VARCHAR(255) NOT NULL,
    "active" BOOLEAN NOT NULL,
    "identify" BOOLEAN NOT NULL
);
ALTER TABLE
    "users" ADD PRIMARY KEY("id");
CREATE TABLE "district"(
    "id" SERIAL NOT NULL,
    "name" VARCHAR(255) NOT NULL,
    "region_id" INTEGER NOT NULL
);
ALTER TABLE
    "district" ADD PRIMARY KEY("id");
CREATE TABLE "temp_send_sms_of_transfer"(
    "id" BIGINT NOT NULL,
    "user_id" VARCHAR(255) NOT NULL,
    "debit_card" VARCHAR(255) NOT NULL,
    "recipient_card" VARCHAR(255) NOT NULL,
    "recipient_full_name" VARCHAR(255) NOT NULL,
    "created_date" DATE NOT NULL,
    "amount" DOUBLE PRECISION NOT NULL,
    "expiration" DATE NOT NULL,
    "transfer_status" INTEGER NOT NULL,
    "sent_code" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "temp_send_sms_of_transfer" ADD PRIMARY KEY("id");
CREATE TABLE "plastic_card"(
    "number" VARCHAR(255) NOT NULL,
    "owner_name" VARCHAR(255) NOT NULL,
    "phone_number" VARCHAR(255) NOT NULL,
    "bank_name" VARCHAR(255) NOT NULL,
    "bank_account_number" VARCHAR(255) NOT NULL,
    "active" BOOLEAN NOT NULL,
    "issued_date" DATE NOT NULL,
    "expiration_date" DATE NOT NULL,
    "type" VARCHAR(255) NOT NULL,
    "user_id" VARCHAR(255) NOT NULL,
    "balance" DOUBLE PRECISION NOT NULL
);
ALTER TABLE
    "plastic_card" ADD PRIMARY KEY("number");
CREATE TABLE "person_identify"(
    "user_id" VARCHAR(255) NOT NULL,
    "first_name" VARCHAR(255) NOT NULL,
    "last_name" VARCHAR(255) NOT NULL,
    "birth_day" DATE NOT NULL,
    "id_card" VARCHAR(255) NOT NULL,
    "issued_date" DATE NOT NULL,
    "expiration" DATE NOT NULL,
    "jshshir" VARCHAR(255) NOT NULL,
    "address" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "person_identify" ADD PRIMARY KEY("user_id");
CREATE TABLE "transfer_status"(
    "id" SERIAL NOT NULL,
    "fee" DOUBLE PRECISION NOT NULL,
    "active" BOOLEAN NOT NULL
);
ALTER TABLE
    "transfer_status" ADD PRIMARY KEY("id");
ALTER TABLE
    "transfer_history" ADD CONSTRAINT "transfer_history_debit_card_foreign" FOREIGN KEY("debit_card") REFERENCES "plastic_card"("number");
ALTER TABLE
    "temp_send_sms_of_transfer" ADD CONSTRAINT "temp_send_sms_of_transfer_debit_card_foreign" FOREIGN KEY("debit_card") REFERENCES "plastic_card"("number");
ALTER TABLE
    "district" ADD CONSTRAINT "district_region_id_foreign" FOREIGN KEY("region_id") REFERENCES "region"("id");
ALTER TABLE
    "temp_send_sms_of_transfer" ADD CONSTRAINT "temp_send_sms_of_transfer_transfer_status_foreign" FOREIGN KEY("transfer_status") REFERENCES "transfer_status"("id");
ALTER TABLE
    "transfer_history" ADD CONSTRAINT "transfer_history_user_id_foreign" FOREIGN KEY("user_id") REFERENCES "users"("id");
ALTER TABLE
    "plastic_card" ADD CONSTRAINT "plastic_card_user_id_foreign" FOREIGN KEY("user_id") REFERENCES "users"("id");
ALTER TABLE
    "temp_send_sms_of_transfer" ADD CONSTRAINT "temp_send_sms_of_transfer_user_id_foreign" FOREIGN KEY("user_id") REFERENCES "users"("id");
ALTER TABLE
    "users" ADD CONSTRAINT "users_id_foreign" FOREIGN KEY("id") REFERENCES "person_identify"("user_id");