package com.mickey.mish.mslfandom;
//AddGemFragment.java, using for add Gem and set your own gem
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import com.mickey.mish.mslfandom.database.Gem;
import com.mickey.mish.mslfandom.database.GemDatabase;

import java.util.Set;
import java.util.TreeSet;

public class AddGemFragment extends Fragment {

    //set classes
    private GridView mTiles;
    private imageAdapter mTileAdapter;
    private ImageView imageBtn;
    private Button buttonAdd;
    private GemDatabase gemDB;
    private EditText[] textViews;
    private Spinner[] spinners;
    private final int[] mInputTextsId = {
            //this is for link the id with entering values you want
            R.id.gemMainInput,
            R.id.gemSubInput1,
            R.id.gemSubInput2,
            R.id.gemSubInput3,
            R.id.gemSubInput4,

    };
    private int selected = R.drawable.pugilist_square_15;
    private final int[] mDrawables = {
            R.drawable.pugilist_square_15,
            R.drawable.intuition_square_15,
            R.drawable.life_square_15,
            R.drawable.siphon_square_15,
            R.drawable.ruin_square_15,
            R.drawable.valor_square_15
    };
    private final int[] spinnersId = {
            R.id.spinner1,
            R.id.spinner2,
            R.id.spinner3,
            R.id.spinner4,
            R.id.spinner5
    };
    //private int[] selectedSpinnerId = {0,0,0,0,0};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { //on create view class
        return inflater.inflate(R.layout.add_gem_fragment,null);
    }

    //on create view class
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] input = new String[mInputTextsId.length];
                for (int i = 0; i < mInputTextsId.length; i++) {
                    input[i] = textViews[i].getText().toString();
                }
                double[] mainStatus = new double[7];
                double[] subStatus = new double[11];
                Gem currentGem = new Gem();


                try {
                    if (checkNoRepeatedSub()){
                    currentGem.mPicId= selected;
                    if (spinners[0].getSelectedItemPosition()==0) {
                        Toast toast3 = Toast.makeText(getContext(), "Failed, Select one",Toast.LENGTH_SHORT);
                        currentGem=null;
                        Log.i("message", "main" +" fails");
                        toast3.show();
                    }else{
                        mainStatus[spinners[0].getSelectedItemPosition() - 1] = Double.parseDouble(input[0]);
                    }
                    if (currentGem!=null) {
                        currentGem.addMain(mainStatus);
                    }

                    for (int i = 1; i< spinners.length; i++){
                        Log.i("message", spinners[i].getSelectedItemPosition() +" ???");
                        if(spinners[i].getSelectedItemPosition()==0) {
                            Toast toast3 = Toast.makeText(getContext(), "Failed ,Select one",Toast.LENGTH_SHORT);
                            currentGem= null;
                            toast3.show();
                            Log.i("message", spinners[i].getSelectedItemPosition() +" fails");
                            break;
                        }else{
                            subStatus[spinners[i].getSelectedItemPosition() - 1] = Double.parseDouble(input[i]);
                        }
                    }
                    if (currentGem!=null) {
                        currentGem.addSub(subStatus);
                    }
                    if (currentGem!=null) {
                        gemDB.gemDao().insertAll(currentGem);
                        Toast toast = Toast.makeText(getContext(), "New Gem Get!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    }
                } catch (NumberFormatException nfe) {
                    Toast toast3 = Toast.makeText(getContext(), "Failed, fulfill all",Toast.LENGTH_SHORT);
                    toast3.show();
                    Log.i("message", "Failed, fulfill all");
                }



                Log.i("message","babababa");

            }
        });


        imageBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.i("message","on click");
                final View popupView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_up_gridview, null);

                mTiles = (GridView) popupView.findViewById(R.id.gridViewPopup);
                // and the adapter for tile data
                if (mTiles==null)
                    Log.i("message","Null gridview");
                mTileAdapter = new imageAdapter();
                mTiles.setAdapter(mTileAdapter);

                final PopupWindow popupWindow = new PopupWindow(popupView,
                        WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                popupWindow.setFocusable(true);

                // If you need the PopupWindow to dismiss when when touched outside
                popupWindow.setBackgroundDrawable(new ColorDrawable());
                popupWindow.setAnimationStyle(R.style.Popup_Animation);

                popupWindow.showAtLocation(v,  Gravity.CENTER, 0, 0);

                if (Build.VERSION.SDK_INT >= 23)
                    popupWindow.setOverlapAnchor(true);


                mTiles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // Object listItem = list.getItemAtPosition(position);
                        imageBtn.setImageResource(mDrawables[position]);
                        imageBtn.getWidth();
                        imageBtn.getHeight();
                        selected = mDrawables[position];
                        Log.i("message","on click my new adapter view" + imageBtn.getHeight() +" " + imageBtn.getWidth());
                        popupWindow.dismiss();
                    }
                });
            }
        });


    }

    /**
     * set image
     * set text
     * set add button
     * once click the add button then link to database for add a new gem
     * @param view
     */
    private void init(View view){

        gemDB = GemDatabase.getINSTANCE(getActivity());
        imageBtn = view.findViewById(R.id.imageAddGEM);
        buttonAdd = view.findViewById((R.id.addGemToDatabase));
        textViews = new EditText[mInputTextsId.length];
        spinners = new Spinner[spinnersId.length];
        for (int i = 0; i< spinners.length; i++){
            spinners[i]= view.findViewById(spinnersId[i]);
        }
        for (int i = 0; i< mInputTextsId.length; i++){
            textViews[i]= view.findViewById(mInputTextsId[i]);
        }

    }

    /**
     * check if there is same gem sub
     * @return true if valid, otherwise false
     */
    public boolean checkNoRepeatedSub (){
        Set<Integer> numbers = new TreeSet<Integer>();

        for (int i = 1; i< spinners.length; i++) {
            Log.i("message", spinners[i].getSelectedItemPosition() + " ???");
            if (!numbers.add(spinners[i].getSelectedItemPosition())) {
                Toast toast3 = Toast.makeText(getContext(), "Repeated sub", Toast.LENGTH_SHORT);
                toast3.show();
                return false;
            }

        }
        return true;
    }

    /**
     * image adapter
     */
    public class imageAdapter extends BaseAdapter {
        // how many tiles
        @Override
        public int getCount() {
            return mDrawables.length;
        }
        // not used
        @Override
        public Object getItem(int i) {
            return null;
        }
        // not used
        @Override
        public long getItemId(int i) {
            return i;
        }

        // populate a view
        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            ImageView image;

            if (convertView == null) {
                // if it's not recycled, inflate it from xml
                convertView = getLayoutInflater().inflate(R.layout.image_adapter, null);
                Log.i("message","on click my new adapter view");
                // convertview will be a LinearLayout
            }else
                // set size to be square
                convertView.setMinimumHeight(mTiles.getWidth() /  mTiles.getNumColumns());
            // get the imageview in this view
            image = (ImageView) convertView.findViewById(R.id.singleImage);
            image.setImageResource(mDrawables[i]);
            image.setTag(i);

            Log.i("message","new adapter view" + i);

            return convertView;
        }
    }
}
