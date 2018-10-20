package space.credifast.credifast.RecyclerViewAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import space.credifast.credifast.R;
import space.credifast.credifast.clases.cAmortiza;
import space.credifast.credifast.fragments.TablaAmortizaFragment.OnListFragmentInteractionListener;
import space.credifast.credifast.fragments.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyTablaAmortizaRecyclerViewAdapter extends RecyclerView.Adapter<MyTablaAmortizaRecyclerViewAdapter.ViewHolder> {

    private final List<cAmortiza> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyTablaAmortizaRecyclerViewAdapter(List<cAmortiza> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_tablaamortiza, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(Integer.toString(mValues.get(position).getFinumpagoamortiza()));
        holder.mContentView.setText(Double.toString(mValues.get(position).getFdpagoamortiza()));
        holder.mCapitalView.setText(Double.toString(mValues.get(position).getFdcapitalpagadoamortiza()));
        holder.mInteresView.setText(Double.toString(mValues.get(position).getFdinteresespagadosamortiza()));
        holder.mAmortiza.setText(Double.toString(mValues.get(position).getFdmontoprestamoamortiza()));


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mCapitalView;
        public final TextView mInteresView;
        public final TextView mAmortiza;

        public cAmortiza mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
            mCapitalView = view.findViewById(R.id.capital);
            mInteresView = view.findViewById(R.id.interes);
            mAmortiza = view.findViewById(R.id.amortiza);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
