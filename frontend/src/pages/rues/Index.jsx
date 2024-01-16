import ArrowDownOnSquareIcon from "@heroicons/react/24/solid/ArrowDownOnSquareIcon";
import PlusIcon from "@heroicons/react/24/solid/PlusIcon";
import {
  Box,
  Button,
  Container,
  Stack,
  SvgIcon,
  Typography,
} from "@mui/material";
import { useCallback, useMemo, useState } from "react";
import { Link, useLoaderData } from "react-router-dom";
import CustomTable from "../../components/ui/CustomTable";
import { applyPagination } from "../../utils/apply-pagination";


const fields = [{label: "id", ident: "id"}, {label:"libelle rue", ident: "lib_rue"}, {ident:["trajet","libTrajet"],label:"libelle trajet"}];

const useRues = (page, rowsPerPage, data) => {
  return useMemo(() => {
    console.log(data);
    return applyPagination(data, page, rowsPerPage);
  }, [page, rowsPerPage, data]);
};


export const Index = () => {
  const data = useLoaderData();

  // const extractedTrajets = [];

  // for (const { trajet } of data) {
  //     extractedTrajets.push(trajet);   
  // }

  // console.log(data);
  
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(5);
  const rues = useRues(page, rowsPerPage, data);

  const handlePageChange = useCallback((event, value) => {
    setPage(value);
  }, []);

  const handleRowsPerPageChange = useCallback((event) => {
    setRowsPerPage(event.target.value);
  }, []);

  return (
    <>
      <Box>
        <title>Rues | Devias Kit</title>
      </Box>
      <Box
        component="main"
        sx={{
          flexGrow: 1,
          py: 8,
        }}
      >
        <Container maxWidth="xl">
          <Stack spacing={3}>
            <Stack direction="row" justifyContent="space-between" spacing={4}>
              <Stack spacing={1}>
                <Typography variant="h4">Rues</Typography>
                <Stack alignItems="center" direction="row" spacing={1}>
                  <Button
                    color="inherit"
                    startIcon={
                      <SvgIcon fontSize="small">
                        <ArrowDownOnSquareIcon />
                      </SvgIcon>
                    }
                  >
                    Export
                  </Button>
                </Stack>
              </Stack>
              <Link to={"/rues/create"}>
                <Button
                  startIcon={
                    <SvgIcon fontSize="small">
                      <PlusIcon />
                    </SvgIcon>
                  }
                  variant="contained"
                >
                  Add
                </Button>
              </Link>
            </Stack>
            <CustomTable
              showAction
              fields={fields}
              count={data.length}
              items={rues}
              onPageChange={handlePageChange}
              onRowsPerPageChange={handleRowsPerPageChange}
              page={page}
              rowsPerPage={rowsPerPage}
              actions={{
                updateActionUrl: (id) => "/rues/" + id + "/edit",
                deleteActionUrl: (id) => "/rues/" + id + "/delete",
                showDetails: (id) => "/trajets/rue-adresses/" + id,
              }}
            />
          </Stack>
        </Container>
      </Box>
    </>
  );
};
