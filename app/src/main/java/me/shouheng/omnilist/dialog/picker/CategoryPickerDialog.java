package me.shouheng.omnilist.dialog.picker;

import android.support.v7.app.AlertDialog;

import java.util.List;

import me.shouheng.omnilist.PalmApp;
import me.shouheng.omnilist.R;
import me.shouheng.omnilist.adapter.picker.CategoryPickerStrategy;
import me.shouheng.omnilist.adapter.picker.ModelsPickerAdapter;
import me.shouheng.omnilist.model.Category;
import me.shouheng.omnilist.model.enums.Status;
import me.shouheng.omnilist.provider.CategoryStore;
import me.shouheng.omnilist.provider.schema.CategorySchema;
import me.shouheng.omnilist.utils.ColorUtils;
import me.shouheng.omnilist.utils.preferences.AssignmentPreferences;
import me.shouheng.omnilist.widget.EmptyView;


/**
 * Created by wangshouheng on 2017/10/5.*/
public class CategoryPickerDialog extends BasePickerDialog<Category> {

    public static CategoryPickerDialog newInstance() {
        return new CategoryPickerDialog();
    }

    @Override
    protected ModelsPickerAdapter<Category> prepareAdapter() {
        return new ModelsPickerAdapter<>(getCategories(), new CategoryPickerStrategy());
    }

    private List<Category> getCategories() {
        return CategoryStore.getInstance().getCategories(null,
                CategorySchema.CATEGORY_ORDER,
                Status.NORMAL,
                AssignmentPreferences.getInstance().showCompleted());
    }

    @Override
    protected void onCreateDialog(AlertDialog.Builder builder, EmptyView emptyView) {
        builder.setTitle(getString(R.string.pick_category));
        builder.setPositiveButton(R.string.text_cancel, null);
        emptyView.setTitle(getString(R.string.no_category_available));
        emptyView.setIcon(ColorUtils.tintDrawable(PalmApp.getDrawableCompact(R.drawable.ic_large_category), getImageTintColor()));
    }

    private int getImageTintColor() {
        return PalmApp.getColorCompact(ColorUtils.isDarkTheme() ?
                R.color.dark_theme_empty_icon_tint_color :
                R.color.light_theme_empty_icon_tint_color);
    }
}
