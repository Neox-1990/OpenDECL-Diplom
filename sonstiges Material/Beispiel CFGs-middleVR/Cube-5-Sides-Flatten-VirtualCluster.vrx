<?xml version="1.0" ?>
<MiddleVR>
    <Kernel LogLevel="2" EnableCrashHandler="0" Version="1.2.1.20130425" />
    <DeviceManager WandAxis="0" WandHorizontalAxis="0" WandHorizontalAxisScale="1" WandVerticalAxis="1" WandVerticalAxisScale="1" WandButtons="0" WandButton0="0" WandButton1="1" WandButton2="2" WandButton3="3" WandButton4="4" WandButton5="5">
        <Driver Type="vrDriverDirectInput" />
    </DeviceManager>
    <DisplayManager Fullscreen="0" WindowBorders="0" ShowMouseCursor="0" VSync="1" SaveRenderTarget="0">
        <Node3D Name="CenterNode" Parent="VRRootNode" Tracker="TrackerSimulatorMouse0.Tracker" UseTrackerX="1" UseTrackerY="1" UseTrackerZ="1" UseTrackerYaw="1" UseTrackerPitch="1" UseTrackerRoll="1" />
        <Node3D Name="HandNode" Tag="Hand" Parent="CenterNode" Tracker="0" PositionLocal="0.000000,0.000000,0.000000" OrientationLocal="0.000000,0.000000,0.000000,1.000000" />
        <Node3D Name="Screens" Parent="CenterNode" Tracker="0" PositionLocal="0.000000,0.000000,1.160000" OrientationLocal="0.000000,0.000000,0.000000,1.000000" />
        <Screen Name="Front" Parent="Screens" Tracker="0" PositionLocal="0.000000,1.200000,0.000000" OrientationLocal="0.000000,0.000000,0.000000,1.000000" Width="3.2" Height="2.4" />
        <Screen Name="Left" Parent="Screens" Tracker="0" PositionLocal="-1.600000,-0.400000,0.000000" OrientationLocal="0.000000,0.000000,0.707107,0.707107" Width="3.2" Height="2.4" />
        <Screen Name="Right" Parent="Screens" Tracker="0" PositionLocal="1.600000,-0.400000,0.000000" OrientationLocal="0.000000,0.000000,-0.707107,0.707107" Width="3.2" Height="2.4" />
        <Screen Name="Floor" Parent="Screens" Tracker="0" PositionLocal="0.000000,0.000000,-1.200000" OrientationLocal="-0.707107,0.000000,0.000000,0.707107" Width="3.2" Height="2.4" />
        <Screen Name="Ceiling" Parent="Screens" Tracker="0" PositionLocal="0.000000,0.000000,1.200000" OrientationLocal="0.707107,0.000000,0.000000,0.707107" Width="3.2" Height="2.4" />
        <Node3D Name="HeadNode" Parent="CenterNode" Tracker="0" PositionLocal="0.000000,0.000000,1.700000" OrientationLocal="0.000000,0.000000,0.000000,1.000000" />
        <CameraStereo Name="CameraStereo_Front" Parent="HeadNode" Tracker="0" PositionLocal="0.000000,0.000000,0.000000" VerticalFOV="60" Near="0.1" Far="3000" Screen="Front" ScreenDistance="1" UseViewportAspectRatio="0" AspectRatio="1.25" InterEyeDistance="0.065" />
        <CameraStereo Name="CameraStereo_Ceiling" Parent="HeadNode" Tracker="0" PositionLocal="0.000000,0.000000,0.000000" VerticalFOV="60" Near="0.1" Far="3000" Screen="Ceiling" ScreenDistance="1" UseViewportAspectRatio="0" AspectRatio="1" InterEyeDistance="0.065" />
        <Node3D Name="Base_Floor" Parent="HeadNode" Tracker="0" PositionLocal="0.000000,0.000000,0.000000" OrientationLocal="0.000000,0.000000,0.000000,1.000000" />
        <CameraStereo Name="CameraStereo_Floor" Parent="Base_Floor" Tracker="0" PositionLocal="0.000000,0.000000,0.000000" VerticalFOV="60" Near="0.1" Far="3000" Screen="Floor" ScreenDistance="1" UseViewportAspectRatio="0" AspectRatio="1" InterEyeDistance="0.065" />
        <CameraStereo Name="CameraStereo_Right" Parent="HeadNode" Tracker="0" PositionLocal="0.000000,0.000000,0.000000" VerticalFOV="60" Near="0.1" Far="3000" Screen="Right" ScreenDistance="1" UseViewportAspectRatio="0" AspectRatio="1.25" InterEyeDistance="0.065" />
        <CameraStereo Name="CameraStereo_Left" Parent="HeadNode" Tracker="0" PositionLocal="0.000000,0.000000,0.000000" VerticalFOV="60" Near="0.1" Far="3000" Screen="Left" ScreenDistance="1" UseViewportAspectRatio="0" AspectRatio="1.25" InterEyeDistance="0.065" />
        <Viewport Name="Viewport_Front" Left="320" Top="200" Width="320" Height="200" Camera="CameraStereo_Front" Stereo="1" StereoMode="0" CompressSideBySide="0" StereoInvertEyes="0" />
        <Viewport Name="Viewport_Left" Left="0" Top="200" Width="320" Height="200" Camera="CameraStereo_Left" Stereo="1" StereoMode="0" CompressSideBySide="0" StereoInvertEyes="0" />
        <Viewport Name="Viewport_Right" Left="640" Top="200" Width="320" Height="200" Camera="CameraStereo_Right" Stereo="1" StereoMode="0" CompressSideBySide="0" StereoInvertEyes="0" />
        <Viewport Name="Viewport_Floor" Left="320" Top="400" Width="320" Height="200" Camera="CameraStereo_Floor" Stereo="1" StereoMode="0" CompressSideBySide="0" StereoInvertEyes="0" />
        <Viewport Name="Viewport_Ceiling" Left="320" Top="0" Width="320" Height="200" Camera="CameraStereo_Ceiling" Stereo="1" StereoMode="0" CompressSideBySide="0" StereoInvertEyes="0" />
    </DisplayManager>
    <Scripts>
        <Script Type="TrackerSimulatorMouse" Name="TrackerSimulatorMouse0" />
    </Scripts>
    <ClusterManager NVidiaSwapLock="0" DisableVSyncOnServer="1" ForceOpenGLConversion="0" BigBarrier="0" SimulateClusterLag="0">
        <ClusterServer Address="localhost" Viewport="Viewport_Front" CPUAffinity="-1" GPUAffinity="-1" />
        <ClusterClient Address="localhost" ClusterID="Client0" Viewport="Viewport_Left" CPUAffinity="-1" GPUAffinity="-1" />
        <ClusterClient Address="localhost" ClusterID="Client1" Viewport="Viewport_Right" CPUAffinity="-1" GPUAffinity="-1" />
        <ClusterClient Address="localhost" ClusterID="Client2" Viewport="Viewport_Floor" CPUAffinity="-1" GPUAffinity="-1" />
        <ClusterClient Address="localhost" ClusterID="Client3" Viewport="Viewport_Ceiling" CPUAffinity="-1" GPUAffinity="-1" />
    </ClusterManager>
</MiddleVR>
