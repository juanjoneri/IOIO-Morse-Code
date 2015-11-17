package ioio.examples.hello;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import ioio.examples.hello.MorseHelpers.Letter;

public class LetterAdapter extends ArrayAdapter<Letter> {

    Context context;
    int layoutResourceId;
    Letter[] data = null;

    public LetterAdapter(Context context,int resource, Letter[] data){
        super(context, resource, data);
        this.context=context;
        this.layoutResourceId=resource;
        this.data=data;
    }

    @Override
    public Letter getItem(int position){
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View row = convertView;

        LayoutInflater inflater = LayoutInflater.from(context);
        row = inflater.inflate(layoutResourceId, parent, false);

        TextView textView = (TextView) row.findViewById(R.id.textRow);
        ImageView imageView = (ImageView) row.findViewById(R.id.imageRow);

        Letter letter = data[position];

        textView.setText(letter.morseCode);

        int resId = context.getResources().getIdentifier(letter.imageName, "drawable", context.getPackageName());
        imageView.setImageResource(resId);

        return row;

    }

}
