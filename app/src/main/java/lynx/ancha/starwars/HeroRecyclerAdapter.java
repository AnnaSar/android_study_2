package lynx.ancha.starwars;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lynx.ancha.starwars.model.DataBase.entity.EntPeoples;
import lynx.ancha.starwars.model.rest.RawPeople;

public class HeroRecyclerAdapter extends RecyclerView.Adapter {

    public interface Listener {
        void onClickPeople(EntPeoples people);
    }

    private static final int VIEW_PROGRESS = 1;
    private static final int VIEW_ITEM = 2;

    private List<EntPeoples> mPeoples = new ArrayList<>();
    private Listener mListener;

//    public void addAll(String[] names) {
//        if (names != null) {
//            mNames.addAll(Arrays.asList(names));
//        }
//        notifyDataSetChanged();
//    }
    public void addAll(List<EntPeoples> peoples) {
        mPeoples.addAll(peoples);
        notifyDataSetChanged();
    }

//    public void clear() {
//        mPeoples.clear();
//        notifyDataSetChanged();
//    }

    public void setListener(Listener listener) {mListener = listener; }

    public void showProgress() {
        if (mPeoples.isEmpty() || !(mPeoples.get(getItemCount() - 1) instanceof EntPeoples.Empty)) {
            mPeoples.add(new EntPeoples.Empty());
            notifyItemInserted(getItemCount() - 1);
        }
    }

    public void hideProgress() {
        if (!mPeoples.isEmpty() && mPeoples.get(getItemCount() - 1) instanceof EntPeoples.Empty) {
            mPeoples.remove(getItemCount() - 1);
            notifyItemRemoved(getItemCount());
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == VIEW_ITEM) {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.layout_for_include_home, viewGroup, false);
            return new HeroViewHolder(view);
        } else {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.layout_item_progress, viewGroup, false);
            return new ProgressViewHolder(view);
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (mPeoples.get(position) instanceof EntPeoples.Empty) {
            return VIEW_PROGRESS;
        } else {
            return VIEW_ITEM;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof HeroViewHolder) {
            ((HeroViewHolder)viewHolder).setPosition(position);
             EntPeoples people = mPeoples.get(position);
            ((HeroViewHolder)viewHolder).bind(people);
        }
    }


//        String name = mNames.get(position);
//        heroViewHolder.bind(name);
//    }

    @Override
    public int getItemCount() {
        return mPeoples.size();
    }

    public class HeroViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mHeroNameTextView;
        private LinearLayout mContainer;

        private int mPosition;

        HeroViewHolder(@NonNull View itemView) {
            super(itemView);
            mContainer = itemView.findViewById(R.id.container);
            mHeroNameTextView = itemView.findViewById(R.id.card_name_text_view);
            mContainer.setOnClickListener(this);
        }

        void setPosition(int position) {
            mPosition = position;
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onClickPeople(mPeoples.get(mPosition));
            }
        }

        public void bind(EntPeoples people) {
            mHeroNameTextView.setText(people.getmName());
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

