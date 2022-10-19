package com.LicuadoraProyectoEcommerce.config;

public class MessagesSwagger {

    public static final String USER_MESSAGE_REFRESH_TOKEN = "{\n" +
            "  \"access_token\": \"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb2RyaWd1ZXphLmZlZGVyYWNpb25AZ21haWwuY29tIiwicm9sZSI6WyJST0xFX1NFTExFUiJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvYXV0aC9yZWZyZXNoIiwiZXhwIjoxNjY1MTUyOTE2fQ.prNrYRQ7F0rWd6WIJOs5BtOEbMBn1xp5SPgiR7EqHRk\",\n" +
            "  \"message\": \"the user rodrigueza.federacion@gmail.com refresh the token succesfully\",\n" +
            "  \"update_token\": \"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb2RyaWd1ZXphLmZlZGVyYWNpb25AZ21haWwuY29tIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2F1dGgvbG9naW4iLCJleHAiOjE2NjUxNTM0NzN9.kW6v4gO5y7dBvtlLCpmpBtP6vxBOf6VqSrV97gFIEhA\"\n" +
            "}";
    public static final String USER_MESSAGE_TOKEN = "{\n" +
            "  \"email\": \"rodrigueza.federacion@gmail.com\",\n" +
            "  \"role\": \"SELLER\",\n" +
            "  \"accessToken\": \"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb2RyaWd1ZXphLmZlZGVyYWNpb25AZ21haWwuY29tIiwicm9sZSI6WyJST0xFX1NFTExFUiJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvYXV0aC9sb2dpbiIsImV4cCI6MTY2NTE4NzY3Mn0.CgUnJtjY0EEbxXpNAMs6gHLtqnMP5oJKvYkwroBoYDQ\",\n" +
            "  \"refreshToken\": \"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb2RyaWd1ZXphLmZlZGVyYWNpb25AZ21haWwuY29tIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2F1dGgvbG9naW4iLCJleHAiOjE2NjUxNTM0NzN9.kW6v4gO5y7dBvtlLCpmpBtP6vxBOf6VqSrV97gFIEhA\"\n" +
            "}";

     public static final String USER_MESSAGE_UPDATE_ROLE = "{\n" +
            "  \"message\": \"the entity was update to role: MANAGER\",\n" +
            "  \"status_code\": 200,\n" +
            "  \"path\": \"http://localhost:8080/auth/2/role\"\n" +
            "}";
    public static final String MESSAGE_DELETE = "{\n" +
            "    \"Message\": \"was deleted success by id 5\"\n" +
            "}";

    public static final String PURCHASE_MESSAGE_UPDATE_STATE = "{\n" +
            "    \"Message\": \"the purchase state was update to ACCEPTED\"\n" +
            "}";

    public static final String CUSTOMIZATION_MESSAGE_RESET_PARAMTERS = "{\n" +
            "    \"Message\": \"the parameter was reset successfully\"\n" +
            "}";;
    public static final String INVOICE_MESSAGE_PDF_CREATED = "{\n" +
            "  \"message\": \"the invoice pdf was create successfully\",\n" +
            "  \"status_code\": 201,\n" +
            "  \"path\": \"/seller/invoice/45/print\"\n" +
            "}";
}
