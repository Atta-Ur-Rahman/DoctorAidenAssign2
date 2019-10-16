package com.techease.aidenassign2application.networking;

public class BaseNetworking {
    public static APIServices services;

    public static APIServices apiServices() {
        services = APIClient.getApiClient().create(APIServices.class);
        return services;
    }

    public static APIServices apiServices(String token) {
        services = APIClient.getApiClient(token).create(APIServices.class);
        return services;
    }
}