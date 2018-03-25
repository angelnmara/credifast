package space.credifast.credifast.RecyclerViewAdapter;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import space.credifast.credifast.R;
import space.credifast.credifast.clases.cButacas;
/*import space.credifast.credifast.fragments.ButacasFragment.OnItemClickListener;*/

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyButacas extends RecyclerView.Adapter<MyButacas.ViewHolder> {

    private final List<cButacas> mValues;
    /*private final OnItemClickListener mListener;*/

    public MyButacas(List<cButacas> items) {

        /*, OnItemClickListener listener*/

        mValues = items;
        /*mListener = listener;*/
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_butacas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getSalaButacaFila());
        holder.mContentView.setText(String.valueOf(mValues.get(position).getSalaButacaColumna()));
        holder.mView.setBackgroundColor(holder.mItem.getVendido() == 1 ? Color.CYAN : Color.WHITE);

        holder.mView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                /*Drawable background = v.getBackground();
                if (background instanceof ColorDrawable)*/

                int guardaVendido = holder.mItem.getVendido();

                if(guardaVendido != 1){
                    //holder.mItem.setVendido(!holder.mItem.getVendido());
                    guardaVendido = ((holder.mItem.getVendido() == 0) ? 2 : 0);
                    holder.mItem.setVendido(guardaVendido);
                    holder.mView.setBackgroundColor((guardaVendido == 2) ? Color.MAGENTA : Color.WHITE );
                    /*if (null != mListener) {
                        // Notify the active callbacks interface (the activity, if the
                        // fragment is attached to one) that an item has been selected.
                        mListener.onClickLista(holder.mItem);
                    }*/
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
        public cButacas mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
