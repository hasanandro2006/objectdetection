package org.hasancottage.objectdetection.fragments;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import org.hasancottage.objectdetection.R;

public class CameraFragmentDirections {
  private CameraFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionCameraToPermissions() {
    return new ActionOnlyNavDirections(R.id.action_camera_to_permissions);
  }
}
