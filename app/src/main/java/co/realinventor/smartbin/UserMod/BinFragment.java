package co.realinventor.smartbin.UserMod;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import co.realinventor.smartbin.Common.Bin;
import co.realinventor.smartbin.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BinFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BinFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BinFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private TextView textViewStatus;
    private ImageView imageViewStatus;
    private DatabaseReference statusRef;
    private String bin_id = "";

    public BinFragment() {
        // Required empty public constructor
    }


    public static BinFragment newInstance() {
        BinFragment fragment = new BinFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();
        if(bundle != null){
            bin_id = bundle.getString("bin_id");
        }

        View view = inflater.inflate(R.layout.fragment_bin, container, false);

        textViewStatus = view.findViewById(R.id.textViewStatus);
        imageViewStatus = view.findViewById(R.id.imageViewStatus);

        statusRef = FirebaseDatabase.getInstance().getReference();

        final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        textViewStatus.setText("Please wait!");

        statusRef.child(bin_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Bin bin = dataSnapshot.getValue(Bin.class);
                if(bin != null){
                    imageViewStatus.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);

                    if(bin.getStat()){
                        textViewStatus.setText("Your bin \nis \nfull");
                        imageViewStatus.setImageResource(R.drawable.trash_full);
                    }
                    else{
                        textViewStatus.setText("Your bin \nis \nNot Full");
                        imageViewStatus.setImageResource(R.drawable.trash_not_full);
                    }


                }
                else{
                    //No bin assigned
                    progressBar.setVisibility(View.GONE);
                    textViewStatus.setText("You have no bin associated with this account! Please contact Admin!");
                    imageViewStatus.setImageResource(R.drawable.trash_full);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
