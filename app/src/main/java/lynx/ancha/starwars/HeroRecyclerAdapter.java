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
import java.util.Arrays;
import java.util.List;

public class HeroRecyclerAdapter extends RecyclerView.Adapter<HeroRecyclerAdapter.HeroViewHolder> {

    private List<String> mNames = new ArrayList<>();

    public void addAll(String[] names) {
        if (names != null) {
            mNames.addAll(Arrays.asList(names));
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HeroViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_for_include_home, viewGroup, false);
        return new HeroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroViewHolder heroViewHolder, int position) {
        heroViewHolder.setPosition(position);
        String name = mNames.get(position);
        heroViewHolder.bind(name);
    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }

    public static class HeroViewHolder
            extends
            RecyclerView.ViewHolder
            implements
            View.OnClickListener {

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
            Log.d("TAG", "mPosition: " + mPosition);
        }

        public void bind(String name) {
            mHeroNameTextView.setText(name);
        }
    }
}
