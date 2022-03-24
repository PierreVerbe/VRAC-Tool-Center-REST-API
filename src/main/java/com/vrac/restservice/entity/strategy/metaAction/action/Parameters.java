package com.vrac.restservice.entity.strategy.metaAction.action;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.vrac.restservice.entity.strategy.metaAction.action.parameters.*;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "action")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ActivateBackGripperAutoGrabParameters.class, name = "ActivateBackGripperAutoGrab"),
        @JsonSubTypes.Type(value = BackGrippersDropCenterParameters.class, name = "BackGrippersDropCenter"),
        @JsonSubTypes.Type(value = BackGrippersDropLeftParameters.class, name = "BackGrippersDropLeft"),
        @JsonSubTypes.Type(value = BackGrippersDropRightParameters.class, name = "BackGrippersDropRight"),
        @JsonSubTypes.Type(value = BackGrippersGrabCenterParameters.class, name = "BackGrippersGrabCenter"),
        @JsonSubTypes.Type(value = BackGrippersGrabLeftParameters.class, name = "BackGrippersGrabLeft"),
        @JsonSubTypes.Type(value = BackGrippersGrabRightParameters.class, name = "BackGrippersGrabRight"),
        @JsonSubTypes.Type(value = BackGrippersInParameters.class, name = "BackGrippersIn"),
        @JsonSubTypes.Type(value = BackGrippersOutParameters.class, name = "BackGrippersOut"),
        @JsonSubTypes.Type(value = BezierParameters.class, name = "Bezier"),
        @JsonSubTypes.Type(value = BottomArmsInDoubleParameters.class, name = "BottomArmsInDouble"),
        @JsonSubTypes.Type(value = BottomArmsInSingleParameters.class, name = "BottomArmsInSingle"),
        @JsonSubTypes.Type(value = BottomArmsOutDoubleParameters.class, name = "BottomArmsOutDouble"),
        @JsonSubTypes.Type(value = BottomArmsOutSingleParameters.class, name = "BottomArmsOutSingle"),
        @JsonSubTypes.Type(value = CalculateOdometryParameters.class, name = "CalculateOdometry"),
        @JsonSubTypes.Type(value = EndParameters.class, name = "End"),
        @JsonSubTypes.Type(value = HomingParameters.class, name = "Homing"),
        @JsonSubTypes.Type(value = LineParameters.class, name = "Line"),
        @JsonSubTypes.Type(value = RotateParameters.class, name = "Rotate"),
        @JsonSubTypes.Type(value = SetDetectionRangeParameters.class, name = "SetDetectionRange"),
        @JsonSubTypes.Type(value = SetOdometryParameters.class, name = "SetOdometry"),
        @JsonSubTypes.Type(value = TopArmGalleryBottomParameters.class, name = "TopArmGalleryBottom"),
        @JsonSubTypes.Type(value = TopArmGalleryTopParameters.class, name = "TopArmGalleryTop"),
        @JsonSubTypes.Type(value = TopArmGetSamplesParameters.class, name = "TopArmGetSamples"),
        @JsonSubTypes.Type(value = TopArmGetSingleSampleParameters.class, name = "TopArmGetSingleSample"),
        @JsonSubTypes.Type(value = TopArmSingleStockageParameters.class, name = "TopArmSingleStockage"),
        @JsonSubTypes.Type(value = TopArmStockageParameters.class, name = "TopArmStockageParameters"),
        @JsonSubTypes.Type(value = WaitParameters.class, name = "WaitParameters"),
        @JsonSubTypes.Type(value = XYTParameters.class, name = "XYT")
})
public abstract class Parameters {
    private String action;
}
