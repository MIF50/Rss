package inter;

import android.view.View;

/**
 * this interface used to implement action of item when put data in view holder
 */

public interface ItemClickListener {
    void onClick(View v,int position,boolean isClickLong);
}
