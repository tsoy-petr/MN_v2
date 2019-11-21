package hootor.com.loftcoint19.screens.welcome;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hootor.com.loftcoint19.R;

public class WelcomeFragment extends Fragment {

    @BindView(R.id.icon)
    ImageView icon;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.subtitle)
    TextView subtitle;

    private Unbinder unbinder;

    private static final String KEY_PAGE = "page";

    public static WelcomeFragment newInstance(WelcomePage page) {

        Bundle args = new Bundle();
        args.putParcelable(KEY_PAGE, page);

        WelcomeFragment fragment = new WelcomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public WelcomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        unbinder = ButterKnife.bind(this, view);

        Bundle args = getArguments();

        if (args != null) {
            WelcomePage page = args.getParcelable(KEY_PAGE);

            if (page != null) {
                icon.setImageResource(page.getIcon());
                title.setText(page.getTitle());
                subtitle.setText(page.getSubTitle());
            }
        }
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
