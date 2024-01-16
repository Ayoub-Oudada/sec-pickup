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



const useAssistantes = (page, rowsPerPage, data) => {
  console.log(data);
  return useMemo(() => {
    console.log(data);
    return applyPagination(data, page, rowsPerPage);
  }, [page, rowsPerPage, data]);
};

const fields = [{label:"id", ident:"id"}, {label:"email", ident:"email"}, {label:"nom", ident:"nom"}, {label:"prenom", ident:"prenom"}, {label:"cni", ident:"cni"}];


const Index = () => {

  const data = useLoaderData();
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(5);
  const Assistantes = useAssistantes(page, rowsPerPage, data);

  const handlePageChange = useCallback((event, value) => {
    setPage(value);
  }, []);

  const handleRowsPerPageChange = useCallback((event) => {
    setRowsPerPage(event.target.value);
  }, []);

  return (
     <>
      <Box>
        <title>Assistantes | Devias Kit</title>
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
                <Typography variant="h4">Assistantes</Typography>
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
              <Link to={"/assistantes/create"}>
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
              fields={fields}
              count={data.length}
              items={Assistantes}
              onPageChange={handlePageChange}
              onRowsPerPageChange={handleRowsPerPageChange}
              page={page}
              rowsPerPage={rowsPerPage}
              actions={{
                updateActionUrl: (id) => "/assistantes/" + id+ "/edit",
                deleteActionUrl: (id) => "/assistantes/" + id + "/delete",
              }}
            />
          </Stack>
        </Container>
      </Box>
    </>
  )
}

export default Index