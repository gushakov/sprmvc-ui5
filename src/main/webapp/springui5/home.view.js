/*
 * Based on examples from https://openui5.hana.ondemand.com/#test-resources/sap/ui/table/demokit/Table.html
 */

sap.ui.jsview("springui5.home", {

    getControllerName: function () {
        return "springui5.home";
    },

    createContent: function (oController) {

        var aControls = [];

        // name input
        var oInput1 = new sap.ui.commons.TextField({
            placeholder: "Fruit name"
        });

        // quantity input
        var oInput2 = new sap.ui.commons.TextField({
            value: {
                path: "value",
                type: new sap.ui.model.type.Integer()
            },
            placeholder: "Fruit quantity"

        });

        // add button
        var oButton = new sap.ui.commons.Button({
            text: "Add",
            press: function () {
                // check if quantity is a number
                if (oInput2.getValueState() !== sap.ui.core.ValueState.Error) {
                    oController.add({
                            // id attribute can be ignored
                            name: oInput1.getValue(),
                            quantity: oInput2.getValue()
                        }
                    );
                }
            }
        });

        // footer layout
        var oLayout = new sap.ui.commons.layout.MatrixLayout({
            columns: 3,
            layoutFixed: false,
            rows: [new sap.ui.commons.layout.MatrixLayoutRow({
                cells: [
                    new sap.ui.commons.layout.MatrixLayoutCell({content: [oInput1]}),
                    new sap.ui.commons.layout.MatrixLayoutCell({content: [oInput2]}),
                    new sap.ui.commons.layout.MatrixLayoutCell({content: [oButton]})
                ]
            })]
        });

        // create table
        var oTable = new sap.ui.table.Table({
            title: "Fruit",
            visibleRowCount: 5,
            selectionMode: sap.ui.table.SelectionMode.Single,
            width: "50%",
            footer: oLayout
        });

        // create columns
        var oColumn1 = new sap.ui.table.Column({
            label: new sap.ui.commons.Label({text: "Name"}),
            template: new sap.ui.commons.InPlaceEdit({
                content: new sap.ui.commons.TextField({
                    value: "{name}"
                }),
                undoEnabled: false,
                change: function (newValue) {
                    oController.update(this.getBindingContext().getObject());
                }
            })
        });

        oTable.addColumn(oColumn1);

        var oColumn2 = new sap.ui.table.Column({
            label: new sap.ui.commons.Label({text: "Quantity"}),
            template: new sap.ui.commons.InPlaceEdit({
                content: new sap.ui.commons.TextField({
                    value: {
                        path: "quantity",
                        type: new sap.ui.model.type.Integer()
                    }
                }),
                undoEnabled: false,
                change: function (newValue) {
                    if (this.getValueState() !== sap.ui.core.ValueState.Error) {
                        oController.update(this.getBindingContext().getObject());
                    }
                }
            })
        });

        oTable.addColumn(oColumn2);

        var oColumn3 = new sap.ui.table.Column({
            label: new sap.ui.commons.Label({text: "Delete"}),
            template: new sap.ui.core.Icon({
                src: sap.ui.core.IconPool.getIconURI("delete"),
                press: function () {
                    // get the id of the bound model object
                    oController.delete(this.getBindingContext().getProperty("id"));
                }
            })
        });

        oTable.addColumn(oColumn3);

        // binding path is the same as the corresponding variable name in HomeModel
        oTable.bindRows({
            path: "/listOfFruit"
        });

        aControls.push(oTable);

        // error message, binding path is the same as the corresponding variable name in HomeModel
        var oText = new sap.ui.commons.TextView({
            text: "{/error}",
            design: sap.ui.commons.TextViewDesign.Bold,
            semanticColor: sap.ui.commons.TextViewColor.Negative
        });

        aControls.push(oText);

        // handle parsing errors

        sap.ui.getCore().attachValidationSuccess(function (oEvent) {
            oEvent.getParameter("element").setValueState(sap.ui.core.ValueState.None);
        });

        sap.ui.getCore().attachParseError(function (oEvent) {
            oEvent.getParameter("element").setValueState(sap.ui.core.ValueState.Error);
        });

        return aControls;
    }

});