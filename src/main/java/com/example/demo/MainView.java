package com.example.demo;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.shared.ThemeVariant;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout{
    private PersonRepository repository;
    private TextField no = new TextField("no");
    private TextField kode = new TextField("kode");
    private EmailField gejala = new EmailField("gejala");
    private Binder<Person> binder =  new Binder<>(Person.class);
    private Grid<Person> grid = new Grid<>(Person.class, false);


    public MainView(PersonRepository repository) {
        this.repository = repository;


        grid.setColumns("id", "kode", "gejala");

        add(getForm(), grid);
        refreshGrid();
    }

    private Component getForm() {
        var layout = new HorizontalLayout();
        layout.setAlignItems(Alignment.BASELINE);

        var addButton = new Button("Tambah");
        addButton.addClickShortcut(Key.ENTER);
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        layout.add(no, kode, gejala, addButton);

        grid.setAllRowsVisible(true);
        binder.bindInstanceFields(this);
  
        addButton.addClickListener(click -> {
            try{
                var person = new Person();
                binder.writeBean(person);
                repository.save(person);
                refreshGrid();
            } catch (ValidationException e) {
                  //
            }
        });


        return layout;
    }

    private void refreshGrid() {
        grid.setItems(repository.findAll());
    }
}
