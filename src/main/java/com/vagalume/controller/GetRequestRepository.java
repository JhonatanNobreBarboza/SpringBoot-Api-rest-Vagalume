package com.vagalume.controller;

import com.google.gson.JsonObject;
import com.vagalume.controller.API;

public class GetRequestRepository {

	private API api;

    public GetRequestRepository(API api) {
        this.api = api;
    }

    public JsonObject getAll(String cantor, String musica) {
        JsonObject jsonObject = null;
        try {
            jsonObject = api.getBuilder(cantor, musica);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JsonObject innerRequest(String uri) {
        JsonObject jsonObject = new JsonObject();
        try {
            jsonObject = api.innerRequest(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
