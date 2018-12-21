package com.mickey.mish.mslfandom;


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
import android.widget.Toast;

import com.mickey.mish.mslfandom.database.Monster;
import com.mickey.mish.mslfandom.database.MonsterDatabase;

/**
 * class of addMonster
 */
public class AddMonsterFragment extends Fragment{
    private GridView mTiles;
    private imageAdapter mTileAdapter;
    private ImageView imageBtn;
    private Button buttonAdd;
    private MonsterDatabase monsterDB;
    private EditText[] textViews;

    /**
     * set what we can edit
     */
    private final int[] mInputTextsId = {R.id.editTextName,
            R.id.editTextHp1,
            R.id.editTextAtk1,
            R.id.editTextDef1,
            R.id.editTextRec1,
            R.id.editTextCritDmg1,
            R.id.editTextCritRate1,
            R.id.editTextRes1,
    };
    private int selected = R.drawable.allmother_odin_light;
    private final int[] mDrawables = {
            R.drawable.allmother_odin_light,
            R.drawable.queen_persephone_light,
            R.drawable.sigrun_light,
            R.drawable.queen_persephone_dark,
            R.drawable.nyx_dark,
            R.drawable.dragonsbane_sigurd_dark,
            R.drawable.myrddin_wyllt_dark,
            R.drawable.sakra_light,
            R.drawable.seimei_fire,
            R.drawable.qitian_dasheng_light,
            R.drawable.selene_light,
            R.drawable.mahakala_light,
            R.drawable.arthur_pendragon_light,
            R.drawable.hattori_hanzo_dark,
            R.drawable.allmother_odin_dark,
            R.drawable.balroxy_wood,
            R.drawable.xuanzang_dashi_water,
            R.drawable.lilith_light,
            R.drawable.trixie_light,
            R.drawable.nalakuvara_dark,
            R.drawable.jack_o_lyn_light
    };


    /**
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return what we selected
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_monster_fragment,null);
    }
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
                Monster currentMonster = null;
                try {

                    currentMonster = new Monster(0, input[0], "", "",
                            selected,
                            Integer.parseInt(input[1]),
                            Integer.parseInt(input[2]),
                            Integer.parseInt(input[3]),
                            Integer.parseInt(input[4]),
                            Double.parseDouble(input[5]),
                            Double.parseDouble(input[6]),
                            Integer.parseInt(input[7]
                            )
                    );
                } catch (NumberFormatException nfe) {
                    System.out.println("Could not parse " + nfe);
                }

                if (currentMonster!=null){
                     monsterDB.monsterDao().insertAll(currentMonster);
                     Toast toast = Toast.makeText(getContext(), "New Pet Get!",Toast.LENGTH_SHORT);
                     Log.i("message",currentMonster.mName + " " + currentMonster.mId);
                     toast.show();
                } else {
                    Toast toast2 = Toast.makeText(getContext(), "Failed",Toast.LENGTH_SHORT);
                    toast2.show();
                }



            }
        });

        /**
         * use the pop window to show the monsters
         */
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
     *
     * @param view
     *      * set image
     *      * set text
     *      * link to monster's database
     */
    private void init(View view){

        imageBtn = view.findViewById(R.id.imageAdd);
        buttonAdd = view.findViewById((R.id.imageButton));
        textViews = new EditText[mInputTextsId.length];
        monsterDB = MonsterDatabase.getINSTANCE(getActivity());
        for (int i = 0; i< mInputTextsId.length; i++){
            textViews[i]= view.findViewById(mInputTextsId[i]);
        }

    }

    /**
     * image adapter for monsters
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
