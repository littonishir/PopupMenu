package com.littonishir.popupmenu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ishirlitton on 2018/1/30.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.TRMViewHolder> {
    private Context mContext;
    private List<MenuItem> menuItemList;
    private boolean showIcon;
    private IshirPopupMenu mIshirPopupMenu;
    private IshirPopupMenu.OnMenuItemClickListener onMenuItemClickListener;

    public Adapter(Context context, IshirPopupMenu ishirPopupMenu, List<MenuItem> menuItemList, boolean show) {
        this.mContext = context;
        this.mIshirPopupMenu = ishirPopupMenu;
        this.menuItemList = menuItemList;
        this.showIcon = show;
    }

    public void setData(List<MenuItem> data){
        menuItemList = data;
        notifyDataSetChanged();
    }

    public void setShowIcon(boolean showIcon) {
        this.showIcon = showIcon;
        notifyDataSetChanged();
    }

    @Override
    public TRMViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.popup_menu_item, parent, false);
        return new TRMViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TRMViewHolder holder, int position) {
        final MenuItem menuItem = menuItemList.get(position);
        if (showIcon){
            holder.icon.setVisibility(View.VISIBLE);
            int resId = menuItem.getIcon();
            holder.icon.setImageResource(resId < 0 ? 0 : resId);
        }else{
            holder.icon.setVisibility(View.GONE);
        }
        holder.text.setText(menuItem.getText());

        if (position == 0){
            holder.container.setBackgroundDrawable(addStateDrawable(mContext, -1, R.drawable.popup_top_pressed));
        }else if (position == menuItemList.size() - 1){
            holder.container.setBackgroundDrawable(addStateDrawable(mContext, -1, R.drawable.popup_bottom_pressed));
        }else {
            holder.container.setBackgroundDrawable(addStateDrawable(mContext, -1, R.drawable.popup_middle_pressed));
        }
        final int pos = holder.getAdapterPosition();
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onMenuItemClickListener != null) {
                    mIshirPopupMenu.dismiss();
                    onMenuItemClickListener.onMenuItemClick(pos);
                }
            }
        });
    }

    private StateListDrawable addStateDrawable(Context context, int normalId, int pressedId){
        StateListDrawable sd = new StateListDrawable();
        Drawable normal = normalId == -1 ? null : context.getResources().getDrawable(normalId);
        Drawable pressed = pressedId == -1 ? null : context.getResources().getDrawable(pressedId);
        sd.addState(new int[]{android.R.attr.state_pressed}, pressed);
        sd.addState(new int[]{}, normal);
        return sd;
    }

    @Override
    public int getItemCount() {
        return menuItemList == null ? 0 : menuItemList.size();
    }

    class TRMViewHolder extends RecyclerView.ViewHolder{
        ViewGroup container;
        ImageView icon;
        TextView text;
        LinearLayout ll_item;

        TRMViewHolder(View itemView) {
            super(itemView);
            container = (ViewGroup) itemView;
            icon = (ImageView) itemView.findViewById(R.id.iv_item_icon);
            text = (TextView) itemView.findViewById(R.id.tv_item_text);
            ll_item = (LinearLayout) itemView.findViewById(R.id.ll_item);
        }
    }

    public void setOnMenuItemClickListener(IshirPopupMenu.OnMenuItemClickListener listener){
        this.onMenuItemClickListener = listener;
    }
}
