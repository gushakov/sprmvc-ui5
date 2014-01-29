sap.ui.controller("springui5.home", {

    onInit: function () {
        this.doAjax("/home").done(this.updateModelData);
    },

    add: function (fruit) {
        this.doAjax("/home/add", fruit).done(this.updateModelData)
            .fail(this.handleAjaxError);
    },

    delete: function (id) {
        this.doAjax("/home/delete/" + id).done(this.updateModelData);
    },

    update: function (fruit) {
        this.doAjax("/home/update", fruit).done(this.updateModelData)
            .fail(this.handleAjaxError);
    },

    doAjax: function (path, content, type, async) {
        var params = {
            url: "/springui5" + path,
            dataType: "json",
            contentType: "application/json",
            context: this,
            cache: false
        };
        params["type"] = type || "POST";
        if (async === false) {
            params["async"] = async;
        }
        if (content) {
            params["data"] = JSON.stringify(content);
        }
        return jQuery.ajax(params);
    },

    updateModelData: function (modelData) {
        console.debug("Ajax response: ", modelData);
        var model = this.getView().getModel();
        if (model == null) {
            // create new JSON model
            this.getView().setModel(new sap.ui.model.json.JSONModel(modelData));
        }
        else {
            // update existing view model
            model.setData(modelData);
            model.refresh();
        }
    },

    handleAjaxError: function (xhr) {
        this.updateModelData(JSON.parse(xhr.responseText));
    }

});