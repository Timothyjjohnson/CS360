package com.example.myapplication;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class GridActivity extends AppCompatActivity {

    private ArrayList<String> inventoryData;
    private InventoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        // Initialize data list
        inventoryData = new ArrayList<>();
        inventoryData.add("Item 1 | 01/01/2024 | 10");
        inventoryData.add("Item 2 | 02/01/2024 | 15");
        inventoryData.add("Item 3 | 03/01/2024 | 5");

        // Set up GridView and custom adapter
        GridView gridView = findViewById(R.id.gridView);
        adapter = new InventoryAdapter(this, inventoryData);
        gridView.setAdapter(adapter);

        // Set up Add Data button
        Button addButton = findViewById(R.id.btnAddData);
        addButton.setOnClickListener(v -> addNewData());

        // Set up item click listener to edit item when clicked
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            openEditDialog(position);
        });
    }

    // Method to add new data to the grid
    private void addNewData() {
        String newItem = "New Item | " + System.currentTimeMillis() + " | " + (int)(Math.random() * 50 + 1); // Dummy data
        inventoryData.add(newItem);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Item added!", Toast.LENGTH_SHORT).show();
    }

    // Method to open the edit dialog and allow user to update item
    private void openEditDialog(int position) {
        Dialog editDialog = new Dialog(this);
        editDialog.setContentView(R.layout.edit_item_dialog);  // Use the dialog layout here

        EditText editItem = editDialog.findViewById(R.id.editItem);
        EditText editDate = editDialog.findViewById(R.id.editDate);
        EditText editQuantity = editDialog.findViewById(R.id.editQuantity);
        Button btnSave = editDialog.findViewById(R.id.btnSave);

        // Populate the fields with current item data
        String currentItem = inventoryData.get(position);
        String[] itemData = currentItem.split(" \\| ");
        editItem.setText(itemData[0]);
        editDate.setText(itemData[1]);
        editQuantity.setText(itemData[2]);

        btnSave.setOnClickListener(v -> {
            String updatedItem = editItem.getText().toString();
            String updatedDate = editDate.getText().toString();
            String updatedQuantity = editQuantity.getText().toString();

            inventoryData.set(position, updatedItem + " | " + updatedDate + " | " + updatedQuantity);
            adapter.notifyDataSetChanged();

            editDialog.dismiss();
            Toast.makeText(this, "Item updated!", Toast.LENGTH_SHORT).show();
        });

        editDialog.show();
    }
}