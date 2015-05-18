<?xml version="1.0" ?>
<MiddleVR>
    <Kernel LogLevel="2" EnableCrashHandler="0" Version="1.1.8.20121112" />
    <DeviceManager WandAxis="0" WandHorizontalAxis="0" WandHorizontalAxisScale="1" WandVerticalAxis="1" WandVerticalAxisScale="1" WandButtons="0" WandButton0="0" WandButton1="1" WandButton2="2" WandButton3="3" WandButton4="4" WandButton5="5">
        <Driver Type="vrDriverDirectInput" />
    </DeviceManager>
    <DisplayManager Fullscreen="0" WindowBorders="0" ShowMouseCursor="1" VSync="0" SaveRenderTarget="0">
        <Node3D Name="CenterNode" Parent="VRRootNode" Tracker="0" PositionLocal="0.000000,0.000000,0.000000" OrientationLocal="0.000000,0.000000,0.000000,1.000000" />
        <Node3D Name="BodyOffset" Parent="CenterNode" Tracker="0" PositionLocal="0.000000,0.000000,1.000000" OrientationLocal="0.000000,0.000000,0.000000,1.000000" />
        <Node3D Name="HeadOffset" Parent="BodyOffset" Tracker="0" PositionLocal="0.000000,0.000000,0.750000" OrientationLocal="0.000000,0.000000,0.000000,1.000000" />
        <Node3D Name="HeadNode" Tag="Head" Parent="HeadOffset" Tracker="0" PositionLocal="0.000000,0.000000,0.000000" OrientationLocal="0.000000,0.000000,0.000000,1.000000" />
        <CameraStereo Name="CameraStereo0" Parent="HeadNode" Tracker="0" PositionLocal="0.000000,0.100000,-0.100000" OrientationLocal="0.000000,0.000000,0.000000,1.000000" VerticalFOV="25.3" Near="0.1" Far="999.934" Screen="0" ScreenDistance="20" UseViewportAspectRatio="1" AspectRatio="1.33333" InterEyeDistance="0.065" />
        <Node3D Name="HandOffset" Parent="BodyOffset" Tracker="0" PositionLocal="-0.400000,0.400000,0.300000" OrientationLocal="0.000000,0.000000,0.000000,1.000000" />
        <Node3D Name="HandNode" Tag="Hand" Parent="HandOffset" Tracker="0" PositionLocal="0.000000,0.000000,0.000000" OrientationLocal="0.000000,0.000000,0.000000,1.000000" />
        <Viewport Name="Viewport0" Left="0" Top="0" Width="1280" Height="720" Camera="CameraStereo0" Stereo="1" StereoMode="3" CompressSideBySide="0" StereoInvertEyes="0" />
    </DisplayManager>
    <ClusterManager NVidiaSwapLock="0" DisableVSyncOnServer="1" ForceOpenGLConversion="0" BigBarrier="0" SimulateClusterLag="0" />
</MiddleVR>
