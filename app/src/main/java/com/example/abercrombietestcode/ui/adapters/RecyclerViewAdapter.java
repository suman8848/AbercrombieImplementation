package com.example.abercrombietestcode.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.abercrombietestcode.R;
import com.example.abercrombietestcode.constants.AppConstants;
import com.example.abercrombietestcode.mvp.model.AbercrombieExploreResponses;
import com.example.abercrombietestcode.mvp.model.Content;
import com.example.abercrombietestcode.ui.activities.AbercrombieWebView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Recycler Adapter for showing the ExploreTabList from Explore Fragment
 */
public class RecyclerViewAdapter
    extends RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder> {
  private final Context mContext;
  private OnItemClickListener listener;
  private List<AbercrombieExploreResponses> exploreFeedItemList;

  public interface OnItemClickListener {
    void onItemClick(AbercrombieExploreResponses item);
  }

  public RecyclerViewAdapter(
      Context context,
      List<AbercrombieExploreResponses> exploreFeedItemList,
      OnItemClickListener listener) {
    this.exploreFeedItemList = exploreFeedItemList;
    this.mContext = context;
    this.listener = listener;
  }

  @NonNull
  @Override
  public RecyclerViewAdapter.CustomViewHolder onCreateViewHolder(
      @NonNull ViewGroup viewGroup, int i) {
    View view =
        LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.frag_explore_adapter, null);
    return new CustomViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int i) {
    final AbercrombieExploreResponses feedItem = exploreFeedItemList.get(i);
    //        Render image using Picasso library
    if (feedItem.getBackgroundImage() != null) {
      Picasso.with(mContext)
          .load(feedItem.getBackgroundImage())
          .error(R.drawable.ic_launcher_background)
          .placeholder(R.drawable.ic_launcher_background)
          .into(customViewHolder.imageView);
    }

    customViewHolder.textViewTitle.setText(feedItem.getTitle());
    customViewHolder.tvTopDescription.setText(feedItem.getTopDescription());
    customViewHolder.tvPromoMessage.setText(feedItem.getPromoMessage());
    // Parsing URL from Json for textview according to android version N
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      if (feedItem.getBottomDescription() != null)
        customViewHolder.tvBottomDescription.setText(
            Html.fromHtml(feedItem.getBottomDescription(), Html.FROM_HTML_MODE_COMPACT) + "");
    } else {
      Html.fromHtml(feedItem.getBottomDescription() + "");
    }
    // Adding buttons according to the content jsonarray and onclick listener
    if (feedItem.getContent() != null && feedItem.getContent().size() > 0) {
      int countTag = 0;
      for (Content content : feedItem.getContent()) {
        LinearLayout.LayoutParams lparams =
            new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        TextView tv = new TextView(mContext);
        lparams.topMargin = 8;
        tv.setTag(countTag);
        tv.setLayoutParams(lparams);
        tv.setText(content.getTitle());
        tv.setGravity(Gravity.CENTER);
        tv.setClickable(true);
        tv.setTextSize(mContext.getResources().getDimension(R.dimen.sp_15));
        tv.setPadding(16, 16, 16, 16);
        tv.setBackground(mContext.getResources().getDrawable(R.drawable.button_shape));
        countTag++;
        customViewHolder.linearLayoutParent.addView(tv);
        tv.setOnClickListener(
            v -> {
              Intent intent =
                  new Intent(mContext.getApplicationContext(), AbercrombieWebView.class);
              intent.putExtra(
                  AppConstants.WEBVIEW_KEY.getKey(),
                  feedItem.getContent().get((int) v.getTag()).getTarget());
              mContext.startActivity(intent);
            });
      }
    }
  }

  @Override
  public int getItemCount() {
    return exploreFeedItemList.size();
  }

  class CustomViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.ivImage)
    protected ImageView imageView;

    @BindView(R.id.tvBottomDescription)
    protected TextView tvBottomDescription;

    @BindView(R.id.tvPromoMessage)
    protected TextView tvPromoMessage;

    @BindView(R.id.tvTopDescription)
    protected TextView tvTopDescription;

    @BindView(R.id.tvTitle)
    protected TextView textViewTitle;

    @BindView(R.id.btn_parent_linear)
    protected LinearLayout linearLayoutParent;

    public CustomViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
