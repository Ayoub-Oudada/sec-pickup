// /* eslint-disable react/prop-types */
// import { PlusIcon } from "@heroicons/react/24/solid";
// import {
//   Box,
//   Button,
//   Card,
//   CardContent,
//   CardHeader,
//   Container,
//   Dialog,
//   DialogActions,
//   DialogContent,
//   DialogContentText,
//   DialogTitle,
//   Divider,
//   FormControl,
//   Grid,
//   InputLabel,
//   MenuItem,
//   Select,
//   Stack,
//   SvgIcon,
//   TextField,
//   Typography,
// } from "@mui/material";
// import { useEffect, useState } from "react";
// import { useLoaderData, useParams } from "react-router-dom";
// import api from "../../utils/api";

// const TrajetRuesDetails = () => {
//   const { trajet, rues: allRues } = useLoaderData();
//   const { trajetId } = useParams();
//   const [current, setCurrent] = useState(trajet.rues[0].id);
//   const [open, setOpen] = useState(false);
//   const [currentRue, setCurrentRue] = useState(
//     ...trajet.rues.filter((rue) => rue.id == current)
//   );
//   useEffect(() => {
//     setCurrentRue(...trajet.rues.filter((rue) => rue.id == current));
//   }, [current, trajet]);

//   return (
//     <>
//       <Box
//         component="main"
//         sx={{
//           flexGrow: 1,
//           py: 8,
//         }}
//       >
//         <Container maxWidth="lg">
//           <Stack
//             direction="row"
//             justifyContent="space-between"
//             spacing={4}
//             sx={{ marginBottom: 3 }}
//           >
//             <Stack spacing={3}>
//               <div>
//                 <Typography variant="h4"></Typography>
//               </div>
//               <div>
//                 <Grid container spacing={4}>
//                   <Grid xs={16} md={12} lg={12}></Grid>
//                 </Grid>
//               </div>
//             </Stack>
//             <Button
//               onClick={() => setOpen(true)}
//               startIcon={
//                 <SvgIcon fontSize="small">
//                   <PlusIcon />
//                 </SvgIcon>
//               }
//               variant="contained"
//             >
//               Attach rue
//             </Button>
//           </Stack>
//           <Card>
//             <CardHeader sx={{ pb: 7 }} title="Trajet informations" />
//             <CardContent
//               sx={{
//                 display: "flex",
//                 justifyContent: "space-between",
//                 pt: 0,
//                 pl: 10,
//                 alignItems: "center",
//               }}
//             >
//               <Box>
//                 {trajet.rues.map((rue, index) => (
//                   <RueItem
//                     text={rue.lib_rue}
//                     isFirst={index == 0}
//                     isLast={index == trajet.rues.length - 1}
//                     index={rue.id}
//                     isCurrent={current == rue.id}
//                     setCurrent={setCurrent}
//                     key={index}
//                   />
//                 ))}
//               </Box>
//               <Card>
//                 <CardHeader sx={{ pb: 7 }} title="Rue informations" />
//                 <CardContent
//                   sx={{
//                     display: "flex",
//                     justifyContent: "space-between",
//                     pt: 0,
//                   }}
//                 >
//                   <Grid container gap={3}>
//                     <InputLabel>Libelle rue </InputLabel>
//                     <Grid xs={12}>
//                       <TextField
//                         disabled
//                         fullWidth
//                         value={currentRue?.lib_rue}
//                       />
//                     </Grid>
//                     <Grid xs={12}>
//                       <InputLabel>Libelle trajet </InputLabel>
//                       <TextField disabled fullWidth value={trajet.libTrajet} />
//                     </Grid>
//                   </Grid>
//                 </CardContent>
//               </Card>
//             </CardContent>
//           </Card>
//         </Container>
//       </Box>
//       <CustomDialog
//         open={open}
//         setOpen={setOpen}
//         rues={allRues}
//         currTrajet={trajetId}
//       />
//     </>
//   );
// };

// const RueItem = ({ text, isFirst, isLast, isCurrent, setCurrent, index }) => {
//   return (
//     <Box
//       sx={[
//         {
//           background: "#5DB075",
//           width: 5,
//           height: isFirst || isLast ? 25 : 45,
//           position: "relative",
//         },
//         isFirst || (isLast && { borderRadius: 30 }),
//       ]}
//     >
//       <Box
//         sx={[
//           {
//             display: "flex",
//             flexDirection: "row",
//             gap: 1,
//             position: "absolute",
//             alignItems: "center",
//             top: 8.9,
//             left: -4.6,
//           },
//           isFirst && { top: -15 },
//           isLast && {
//             bottom: -25,
//           },
//         ]}
//       >
//         <Box
//           sx={{
//             width: 14,
//             height: 14,
//             backgroundColor: "#5DB075",
//             borderRadius: 7,
//           }}
//         />
//         <Button
//           sx={{ textAlign: "start" }}
//           variant={isCurrent ? "contained" : "text"}
//           onClick={() => setCurrent(index)}
//         >
//           <Typography sx={{ flex: 1, width: 300 }} variant="body1">
//             {text}
//           </Typography>
//         </Button>
//       </Box>
//     </Box>
//   );
// };

// const CustomDialog = ({ open, setOpen, rues, currTrajet }) => {
//   const handleClose = () => {
//     setOpen(false);
//   };
//   const [selectVal, setSelectedVal] = useState("");
//   useEffect(() => {
//     console.log(selectVal);
//   }, [selectVal]);

//   const submit = async () => {
//     await api.put("/api/rues/" + selectVal.id, {
//       trajetId: currTrajet,
//       lib_rue: selectVal.lib_rue,
//     });
//     setOpen(false);
//   };

//   return (
//     <>
//       <Dialog
//         open={open}
//         keepMounted
//         onClose={handleClose}
//         aria-describedby="alert-dialog-slide-description"
//         sx={{
//           height: 1000,
//         }}
//       >
//         <DialogTitle>{"Attach rue"}</DialogTitle>
//         <DialogContent>
//           <DialogContentText id="alert-dialog-slide-description">
//             You can attach any rue to your trajet, so please select a trajet.
//           </DialogContentText>
//           <FormControl variant="filled" sx={{ mt: 3, mb: 3 }} fullWidth>
//             <InputLabel id="demo-simple-select-filled-label">Rue</InputLabel>
//             <Select
//               labelId="demo-simple-select-filled-label"
//               id="demo-simple-select-filled"
//               value={selectVal.lib_rue}
//               onChange={(e) => {
//                 setSelectedVal(
//                   ...rues.filter((rue) => rue.id == e.target.value)
//                 );
//               }}
//             >
//               <MenuItem value="">
//                 <em>None</em>
//               </MenuItem>
//               {rues.map((rue) => (
//                 <MenuItem value={rue.id} key={rue.id}>
//                   {rue.lib_rue}
//                 </MenuItem>
//               ))}
//             </Select>
//           </FormControl>
//         </DialogContent>
//         <DialogActions>
//           <Button onClick={handleClose}>Cancel</Button>
//           <Button onClick={submit} variant="contained">
//             Confirm
//           </Button>
//         </DialogActions>
//       </Dialog>
//     </>
//   );
// };

// export default TrajetRuesDetails;
