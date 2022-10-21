package pt.ipbeja.twdm.pdm2.swapiapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.PersonListViewHolder> {
    List<Person> persons = new ArrayList<>();

    public PersonListAdapter() {
    }

    @NonNull
    @Override
    public PersonListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_person, parent, false);
        return new PersonListViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonListViewHolder holder, int position) {
        Person person = this.persons.get(position);

        holder.setName(person.getName());
        holder.setHeight(person.getHeight());
        holder.setMass(person.getMass());

    }

    public void updateData(List<Person> persons){
        this.persons = persons;
        notifyDataSetChanged();
    }

    public void insertData(List<Person> persons){
        int previousMax = this.persons.size();
        this.persons.addAll(persons);
        int currentMax = this.persons.size();
        notifyItemRangeChanged(previousMax, currentMax);
    }
    @Override
    public int getItemCount() {
        return persons.size();
    }

    public class PersonListViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewPersonName;
        private TextView textViewPersonHeight;
        private TextView textViewPersonMass;

        public PersonListViewHolder(@NonNull View itemView) {
            super(itemView);

            this.textViewPersonName = itemView.findViewById(R.id.textViewPersonName);
            this.textViewPersonHeight = itemView.findViewById(R.id.textViewPersonHeight);
            this.textViewPersonMass = itemView.findViewById(R.id.textViewPersonMass);
        }

        public void setName(String name){
            this.textViewPersonName.setText(String.format("Name: %s", name));
        }

        public void setHeight(String height){
            String ending = "cm";
            if(height.equals("unknown")){
                ending = "";
            }
            this.textViewPersonHeight.setText(String.format("Height: %s %s", height, ending));
        }

        public void setMass(String mass){
            String ending = "Kg";
            if(mass.equals("unknown")){
                ending = "";
            }
            this.textViewPersonMass.setText(String.format("Mass: %s %s", mass, ending));
        }
    }
}
