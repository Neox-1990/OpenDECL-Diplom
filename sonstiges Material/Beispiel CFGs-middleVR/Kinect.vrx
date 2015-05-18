<?xml version="1.0" ?>
<MiddleVR>
    <Kernel LogLevel="2" EnableCrashHandler="0" Version="1.0.8.20121008" />
    <DeviceManager WandAxis="0" WandHorizontalAxis="0" WandHorizontalAxisScale="1" WandVerticalAxis="1" WandVerticalAxisScale="1" WandButtons="0" WandButton0="0" WandButton1="1" WandButton2="2" WandButton3="3" WandButton4="4" WandButton5="5">
        <Driver Type="vrDriverDirectInput" />
        <Driver Type="vrDriverKinect" />
    </DeviceManager>
    <DisplayManager Fullscreen="0" WindowBorders="0" ShowMouseCursor="1" VSync="1" SaveRenderTarget="0">
        <Node3D Name="CenterNode" Parent="VRRootNode" Tracker="0" PositionLocal="0.000000,0.000000,0.000000" OrientationLocal="0.000000,0.000000,0.000000,1.000000" />
        <Node3D Name="Kinect0.RootNode" Parent="CenterNode" Tracker="0" PositionLocal="0.000000,0.000000,0.000000" OrientationLocal="0.000000,0.000000,0.000000,1.000000" />
        <Node3D Name="Kinect0.User0.HipCenter_Node" Parent="Kinect0.RootNode" Tracker="Kinect0.User0.HipCenter" />
        <Node3D Name="Kinect0.User0.Spine_Node" Parent="Kinect0.RootNode" Tracker="Kinect0.User0.Spine" />
        <Node3D Name="Kinect0.User0.ShoulderCenter_Node" Parent="Kinect0.RootNode" Tracker="Kinect0.User0.ShoulderCenter" />
        <Node3D Name="Kinect0.User0.Head_Node" Parent="Kinect0.RootNode" Tracker="Kinect0.User0.Head" />
        <Node3D Name="HeadNode" Tag="Head" Parent="Kinect0.User0.Head_Node" Tracker="0" PositionLocal="0.000000,0.000000,0.000000" OrientationLocal="0.000000,0.000000,0.000000,1.000000" />
        <Camera Name="Camera0" Parent="HeadNode" Tracker="0" PositionLocal="0.000000,0.000000,0.000000" OrientationLocal="0.000000,0.000000,0.000000,1.000000" VerticalFOV="60" Near="0.1" Far="1000" Screen="0" ScreenDistance="1" UseViewportAspectRatio="1" AspectRatio="1.33333" />
        <Node3D Name="Kinect0.User0.Left_Shoulder_Node" Parent="Kinect0.RootNode" Tracker="Kinect0.User0.Left_Shoulder" />
        <Node3D Name="Kinect0.User0.Left_Elbow_Node" Parent="Kinect0.RootNode" Tracker="Kinect0.User0.Left_Elbow" />
        <Node3D Name="Kinect0.User0.Left_Wrist_Node" Parent="Kinect0.RootNode" Tracker="Kinect0.User0.Left_Wrist" />
        <Node3D Name="Kinect0.User0.Left_Hand_Node" Parent="Kinect0.RootNode" Tracker="Kinect0.User0.Left_Hand" />
        <Node3D Name="Kinect0.User0.Right_Shoulder_Node" Parent="Kinect0.RootNode" Tracker="Kinect0.User0.Right_Shoulder" />
        <Node3D Name="Kinect0.User0.Right_Elbow_Node" Parent="Kinect0.RootNode" Tracker="Kinect0.User0.Right_Elbow" />
        <Node3D Name="Kinect0.User0.Right_Wrist_Node" Parent="Kinect0.RootNode" Tracker="Kinect0.User0.Right_Wrist" />
        <Node3D Name="Kinect0.User0.Right_Hand_Node" Parent="Kinect0.RootNode" Tracker="Kinect0.User0.Right_Hand" />
        <Node3D Name="HandNode" Tag="Hand" Parent="Kinect0.User0.Right_Hand_Node" Tracker="0" PositionLocal="0.000000,0.000000,0.000000" OrientationLocal="0.000000,0.000000,0.000000,1.000000" />
        <Node3D Name="Kinect0.User0.Left_Hip_Node" Parent="Kinect0.RootNode" Tracker="Kinect0.User0.Left_Hip" />
        <Node3D Name="Kinect0.User0.Left_Knee_Node" Parent="Kinect0.RootNode" Tracker="Kinect0.User0.Left_Knee" />
        <Node3D Name="Kinect0.User0.Left_Ankle_Node" Parent="Kinect0.RootNode" Tracker="Kinect0.User0.Left_Ankle" />
        <Node3D Name="Kinect0.User0.Left_Foot_Node" Parent="Kinect0.RootNode" Tracker="Kinect0.User0.Left_Foot" />
        <Node3D Name="Kinect0.User0.Right_Hip_Node" Parent="Kinect0.RootNode" Tracker="Kinect0.User0.Right_Hip" />
        <Node3D Name="Kinect0.User0.Right_Knee_Node" Parent="Kinect0.RootNode" Tracker="Kinect0.User0.Right_Knee" />
        <Node3D Name="Kinect0.User0.Right_Ankle_Node" Parent="Kinect0.RootNode" Tracker="Kinect0.User0.Right_Ankle" />
        <Node3D Name="Kinect0.User0.Right_Foot_Node" Parent="Kinect0.RootNode" Tracker="Kinect0.User0.Right_Foot" />
        <Viewport Name="Viewport0" Left="0" Top="0" Width="1280" Height="800" Camera="Camera0" Stereo="0" StereoMode="3" CompressSideBySide="0" StereoInvertEyes="0" />
    </DisplayManager>
    <ClusterManager NVidiaSwapLock="0" DisableVSyncOnServer="1" ForceOpenGLConversion="0" BigBarrier="0" SimulateClusterLag="0" />
</MiddleVR>
